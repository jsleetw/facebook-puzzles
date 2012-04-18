var fs = require('fs')
var _ = require('underscore')

var statuses = [];
var casesNumber = 0;


// Reading stuff

fs.readFileSync(process.argv[2]).toString().split('\n').forEach(function(line) {
  if (casesNumber == 0) {
    casesNumber = parseInt(line);
    return;
  }
  
  if (!line) {
    return;
  }
  
  var caseId = statuses.length; // Starting from 0
  
  
  if (statuses[caseId - 1] && statuses[caseId - 1]['max'] && !statuses[caseId - 1]['message']) {
    statuses[caseId - 1]['message'] = line;
    return;
  }
  
  if (caseId == 0 || !statuses[caseId] ||  !statuses[caseId]['max']) {      
      statuses[caseId] = {};

      var splitted = line.split(/\s+/);
      
      statuses[caseId].max = splitted[0];
      if (splitted[1]) {
        statuses[caseId]['message'] = splitted[1];
      }
      return;
  }

});

function getNumberOfStatuses(maxChar, status) {
  console.log("getNumberOfStatuses: "+ maxChar + " : " + status)
    // 
    // if (cache[status]) {
    //   console.log("Cache hit")
    //   return 0;
    // }
    
  if (status.match(/^0/)) {
    return 0;
  }
  
  if (status.length == 0) {
    return 0;
  }
  
  if (status.length == 1) {
    console.log("     => Size 1")
    cache[status] = parseInt(status) < parseInt(maxChar) ? 1 : 0;
    return parseInt(status) < maxChar ? 1 : 0;
  }

  
  var sum = 0;
  console.log("Checking different cases:")
  for (var i=1; i<=Math.min(maxChar.length, status.length - 1);i++) {
    var firstPiece = status.substr(0, i);
    var secondPiece = status.substr(i, status.length - i);
    console.log(i  + " -> length("+status+") = length(" + firstPiece + ") + length(" + secondPiece + ")")
    sum += (getNumberOfStatuses(maxChar, firstPiece) * getNumberOfStatuses(maxChar, secondPiece));
    console.log("   "+i+"  => Sum: " + sum)
  }

  
  console.log("Results with " + sum + " different statuses in " + status)
  
  cache[status] = sum;
  return sum;
  
}

var resultCaseId = 0;
_.each(statuses, function(status) {
  resultCaseId++;
  cache = {};
  console.log("Case #"+resultCaseId+": " + getNumberOfStatuses(status.max, status.message));
})
