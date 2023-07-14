window.addEventListener("DOMContentLoaded", (event) => {
  console.log("DOM fully loaded and parsed");
  document.querySelector('#post-upvote-btn').addEventListener("click", (event) => {
      upvote(event);
  })
});
async function putData(data) {
    var postId = document.getElementById('post-id').value;
    console.log(data)
  // Default options are marked with *
  try {
    const response = await fetch(`/${postId}/vote`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const result = await response.json();
    console.log("Success:", result);
  } catch (error) {
    console.error("Error:", error);
  }
}
function upvote(event) {
    console.log(event.target.dataset.posttype);
    // if document.getElementByID('post-upvoted').value == false else decrement
    incrementVote(event);
    var data =  {
        voteType: "upvote",
    }
    putData(data);
}

function incrementVote(event) {
    var countElId = `${event.target.dataset.posttype}-${event.target.dataset.votetype}-count`;
    var upVoteCountEl = document.getElementById(countElId);
    upVoteCountEl.innerText = parseInt(upVoteCountEl.innerText) + 1
    // TODO: send put request to update DB
}

function decrementVote(event) {
    var countElId = `${event.target.dataset.posttype}-${event.target.dataset.votetype}-count`;
    var upVoteCountEl = document.getElementById(countElId);
    upVoteCountEl.innerText = parseInt(upVoteCountEl.innerText) - 1
    // TODO: send put request to update DB
}

function postVote() {
    // Put request to post or comment adding user to votes list
}

function toggleUpvoted() {
}

function toggleDownVoted() {

}

function disableVoteBtn() {
}