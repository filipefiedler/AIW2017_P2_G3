window.onload = function(){
  var newsHeads = document.getElementsByClassName("news-head");

  function displaySummary(){
    var myNewsBody = this.parentElement.children[1];
    if (myNewsBody.style.display=='none') {
      myNewsBody.style.display='block';
    } else {
      myNewsBody.style.display='none';
    }
  }
  for (var i=0; i<newsHeads.length; i++){
    newsHeads[i].addEventListener('click', displaySummary, false);
  }
}
