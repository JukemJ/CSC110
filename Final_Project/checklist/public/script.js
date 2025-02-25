document.getElementById('addTaskButton').addEventListener('click', createNewTask);

for(let x of document.getElementsByClassName('deleteButton')){
    x.addEventListener('click', deleteTask);
}

for(let x of document.getElementsByClassName('completeButton')){
    //x.addEventListener('click', markTaskComplete);
    if(x.textContent == "Completed"){
       // x.removeEventListener('click', markTaskComplete);
        x.addEventListener('click', markTaskIncomplete);
    }
    else if(x.textContent == "Incomplete"){
        //x.removeEventListener('click', markTaskIncomplete);
        x.addEventListener('click', markTaskComplete);
    }
}


async function createNewTask(){
    taskText = document.getElementById('newTaskText').value;
    const task = {completed: false, color: "none", text: taskText}
    const response = await fetch("/create", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(task)
    });

    if(response.status == 200){
        const checklist = document.getElementById('checklist');
        checklist.appendChild(createTaskDiv(taskText));
        console.log('New Task Created!');
    }
    else {
        alert('Failed to create new task!');
        return
    }
    
}

function createTaskDiv(taskText) {
    // Create the main container div
    const taskDiv = document.createElement('div');
    taskDiv.className = 'tasks row justify-content-around my-2';
    taskDiv.id = 'checklist';

    // Create the "Complete" button
    const completeBtn = document.createElement('button');
    completeBtn.className = 'btn btn-warning col-2';
    completeBtn.textContent = 'Incomplete';
    completeBtn.addEventListener('click', markTaskComplete);

    // Create the "X" button
    const deleteBtn = document.createElement('button');
    deleteBtn.className = 'btn btn-danger col-1';
    deleteBtn.textContent = 'X';
    deleteBtn.addEventListener('click', deleteTask);

    // Create the task text span
    const taskSpan = document.createElement('span');
    taskSpan.className = 'task-text col-8';
    taskSpan.textContent = taskText; // Insert dynamic text

    // Append elements to the main div
    taskDiv.appendChild(completeBtn);
    taskDiv.appendChild(deleteBtn);
    taskDiv.appendChild(taskSpan);

    return taskDiv;
}

async function deleteTask(){
    const taskText = this.parentElement.querySelector('.task-text').textContent;
    const response = await fetch("/delete", {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({text: taskText})
    });

    if(response.status == 200){
        this.parentElement.remove();
        console.log('Task Deleted!');
    }
    else {
        alert('Failed to delete task!');
        return
    }
}

async function markTaskComplete(){
    const taskText = this.parentElement.querySelector('.task-text');
    const completeBtn = this.parentElement.querySelector('.btn-warning');
    const response = await fetch("/complete", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({text: taskText.textContent})
    });

    if(response.status == 200){
        completeBtn.textContent = "Completed";
        taskText.style.textDecoration = 'line-through';
        completeBtn.removeEventListener('click', markTaskComplete);
        completeBtn.addEventListener('click', markTaskIncomplete);
        completeBtn.classList.remove("btn-warning");
        completeBtn.classList.add("btn-success");
        
        console.log('Task Completed!');
    }
    else {
        alert('Failed to complete task!');
        return
    }
}

async function markTaskIncomplete(){
    const taskText = this.parentElement.querySelector('.task-text');
    const completeBtn = this.parentElement.querySelector('.btn-success');
    
    const response = await fetch("/incomplete", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({text: taskText.textContent})
    });

    if(response.status == 200){
        completeBtn.textContent = "Incomplete";
        taskText.style.textDecoration = 'none';
        completeBtn.removeEventListener('click', markTaskComplete);
        completeBtn.addEventListener('click', markTaskIncomplete);
        completeBtn.classList.remove("btn-primary");
        completeBtn.classList.add("btn-warning");

        console.log('Task Marked Incomplete!');
    }
    else {
        alert('Failed to complete task!');
        return
    }
}