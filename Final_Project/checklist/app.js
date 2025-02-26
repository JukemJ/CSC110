const express = require('express');
const fs = require('fs');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;
const DATA_FILE = path.join(__dirname, 'db.json');

// Middleware
app.use(bodyParser.json());
app.use(express.static('public'));
app.set('view engine', 'ejs');
//app.use(express.urlencoded({ extended: true }));

const DB = JSON.parse(fs.readFileSync(DATA_FILE, 'utf8'));

// Serve HTML Page
app.get('/', (req, res) => {    
    res.render('index', {db: DB});
});

// Delete tasks
app.delete('/delete', (req, res) => {
    let text = req.body.text;
    DB.tasks = DB.tasks.filter(task => task.text != text);

    fs.writeFile(DATA_FILE, JSON.stringify(DB), (err) => {
        if (err) {
            return res.status(500).json({ error: 'Failed to write data file' });
        }
        res.sendStatus(200);
    });
});

// Create tasks
app.post('/create', (req, res) => {
    let obj = req.body;
    obj.id = DB.highestId++;
    DB.tasks.push(obj);

    fs.writeFile(DATA_FILE, JSON.stringify(DB), (err) => {
        if (err) {
            return res.status(500).json({ error: 'Failed to write data file' });
        }
        res.sendStatus(200);
    });
});

// Complete tasks
app.post('/complete', (req, res) => {
    let obj = DB.tasks.find(task => task.text == req.body.text);
    obj.completed = true;

    fs.writeFile(DATA_FILE, JSON.stringify(DB), (err) => {
        if (err) {
            return res.status(500).json({ error: 'Failed to write data file' });
        }
        res.sendStatus(200);
    });
});

// Incomplete tasks
app.post('/incomplete', (req, res) => {
    let obj = DB.tasks.find(task => task.text == req.body.text);
    obj.completed = false;

    fs.writeFile(DATA_FILE, JSON.stringify(DB), (err) => {
        if (err) {
            return res.status(500).json({ error: 'Failed to write data file' });
        }
        res.sendStatus(200);
    });
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
