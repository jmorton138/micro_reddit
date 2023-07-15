window.addEventListener("DOMContentLoaded", (event) => {
  console.log("DOM fully loaded and parsed");
  document.querySelector('#post-upvote-btn').addEventListener("click", (event) => {
      vote(event);
  });
   document.querySelector('#post-downvote-btn').addEventListener("click", (event) => {
        vote(event);
   });
});
async function putData(data) {
  var postId = document.getElementById('post-id').value;
  try {
    const response = await fetch(`/posts/${postId}/vote`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const result = await response.json();
  } catch (error) {
        console.log("Error:", error);
  }
}
function decrementIfVotedNonTargeted(postType, nonTargetedVoteType) {
    var postId = document.getElementById('post-id').value;
    let url = `/posts/${postId}/votes/vote-type/${nonTargetedVoteType}`;
    console.log(url);
    fetch(url, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then(result => result.json())
        .then(data => {
            if (data == true) {
              decrementVote(postType, nonTargetedVoteType);
            }
        });
}

function vote(event) {
    var targetedVoteType = event.target.dataset.votetype;
    var nonTargetedVoteType = (targetedVoteType == 'upvote') ? 'downvote' : 'upvote';
    if (event.target.classList.contains('not-voted')) {
        incrementVote(event.target.dataset.posttype, targetedVoteType);
       decrementIfVotedNonTargeted(event.target.dataset.posttype, nonTargetedVoteType);
    } else if (event.target.classList.contains('voted')) {
        decrementVote(event.target.dataset.posttype, targetedVoteType);
    }

}
function incrementVote(postType, voteType) {
    var countElId = `${postType}-${voteType}-count`;
    var voteCountEl = document.getElementById(countElId);
    var voteBtnId = `${postType}-${voteType}-btn`;
    var voteBtn = document.getElementById(voteBtnId);
    voteBtn.classList.remove('not-voted');
    voteBtn.classList.add('voted');
    voteCountEl.innerText = parseInt(voteCountEl.innerText) + 1
    var data =  {
        voteType: voteType,
    }
    putData(data);
}
//function incrementVote(event) {
//    var countElId = `${event.target.dataset.posttype}-${event.target.dataset.votetype}-count`;
//    var upVoteCountEl = document.getElementById(countElId);
//    upVoteCountEl.innerText = parseInt(upVoteCountEl.innerText) + 1
//    // TODO: send put request to update DB
//}

function decrementVote(postType, voteType) {
    var countElId = `${postType}-${voteType}-count`;
    var upVoteCountEl = document.getElementById(countElId);
    var voteBtnId = `${postType}-${voteType}-btn`;
    var voteBtn = document.getElementById(voteBtnId);
    voteBtn.classList.remove('voted');
    voteBtn.classList.add('not-voted');
    upVoteCountEl.innerText = parseInt(upVoteCountEl.innerText) - 1
    // TODO: send put request to update DB
    var data =  {
        voteType: voteType,
    }
    putData(data);
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