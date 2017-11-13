window.onload = function(){
  var newsHeads = document.getElementsByClassName("news-head");

  function displaySummary(){
    var myNewsBody = this.parentElement.children[1];
    if (myNewsBody.style.display=='block') {
      myNewsBody.style.display='none';
    } else {
      myNewsBody.style.display='block';
    }
  }
  for (var i=0; i<newsHeads.length; i++){
    newsHeads[i].addEventListener('click', displaySummary, false);
  }

  var firstSummary = document.getElementsByClassName("news-body")[0];
  firstSummary.style.display = 'block';

}
