window.addEventListener("DOMContentLoaded", (event) => {
  console.log("DOM fully loaded and parsed");
});

async function putData(data, postId) {
     const token = document.querySelector('meta[name="_csrf"]').content;
     const header = document.querySelector('meta[name="_csrf_header"]').content;
  try {
    const response = await fetch(`/posts/${postId}/vote`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": token,
      },
      body: JSON.stringify(data),
    });
    const result = await response.json();
  } catch (error) {
        console.log("Error:", error);
  }
}

function decrementIfVotedNonTargeted(postType, nonTargetedVoteType, postId) {
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
              decrementVote(postType, nonTargetedVoteType, postId);
            }
        });
}

function vote(event) {
    var targetedVoteType = event.target.dataset.votetype;
    var nonTargetedVoteType = (targetedVoteType == 'upvote') ? 'downvote' : 'upvote';
    if (event.target.classList.contains('not-voted')) {
        incrementVote(event.target.dataset.posttype, targetedVoteType, event.target.dataset.id);
       decrementIfVotedNonTargeted(event.target.dataset.posttype, nonTargetedVoteType, event.target.dataset.id);
    } else if (event.target.classList.contains('voted')) {
        decrementVote(event.target.dataset.posttype, targetedVoteType, event.target.dataset.id);
    }

}

function incrementVote(postType, voteType, postId) {
    var countElId = `${postType}-${voteType}-count-${postId}`;
    var voteCountEl = document.getElementById(countElId);
    var voteBtnId = `${postType}-${voteType}-btn-${postId}`;
    var voteBtn = document.getElementById(voteBtnId);
    voteBtn.classList.remove('not-voted');
    voteBtn.classList.add('voted');
    voteCountEl.innerText = parseInt(voteCountEl.innerText) + 1
    var data =  {
        voteType: voteType,
    }
    putData(data, postId);
}

function decrementVote(postType, voteType, postId) {
    var countElId = `${postType}-${voteType}-count-${postId}`;
    var upVoteCountEl = document.getElementById(countElId);
    var voteBtnId = `${postType}-${voteType}-btn-${postId}`;
    var voteBtn = document.getElementById(voteBtnId);
    voteBtn.classList.remove('voted');
    voteBtn.classList.add('not-voted');
    upVoteCountEl.innerText = parseInt(upVoteCountEl.innerText) - 1
    var data =  {
        voteType: voteType,
    }
    putData(data, postId);
}
