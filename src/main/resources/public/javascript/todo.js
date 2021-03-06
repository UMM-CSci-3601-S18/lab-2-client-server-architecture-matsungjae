/**
 * Function to get all the todos!
 */
function getAllTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();
  HttpThingy.get("/api/todos", function(returned_json){
    document.getElementById('jsonGO').innerHTML = returned_json;
  });
}

function getFilteredTodos() {
  console.log("Getting all the users.");

  var HttpThingy = new HttpClient();
  var finalUrl = "/api/todos?";
  //each filter will be concatenated onto the string (ex: finalUrl += "category=homework&"

  //filter by category
  var category = document.getElementById("category").value;
  if (category != null && category !== "") {
    finalUrl += "category=" + category + "&";
  }
  //filter by body
  var body = document.getElementById("body").value;
  if (body != null && body !== "") {
    finalUrl += "body=" + body + "&";
  }
  //filter by owner
  var owner = document.getElementById("owner").value;
  if (owner != null && owner !== "") {
    finalUrl += "owner=" + owner + "&";
  }
  //filter by status
  if (document.getElementById("complete").checked === true) {
    finalUrl += "status=complete&";
  }
  else if (document.getElementById("incomplete").checked === true) {
    finalUrl += "status=incomplete&";
  }
  //sort alphabetically by a something
  var orderBy = document.getElementById("orderBy").value;
  if(orderBy != null && orderBy !== "") {
    finalUrl += "orderBy=" + orderBy + "&";
  }

  //limit number of todos displayed
  var limit = document.getElementById("limit").value;
  if(limit != null && limit !== 0) {
    finalUrl += "limit=" + limit;
  }


  HttpThingy.get(finalUrl, function(returned_json){
    document.getElementById('jsonGO').innerHTML = returned_json;
  });
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function(aUrl, aCallback){
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function(){

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
