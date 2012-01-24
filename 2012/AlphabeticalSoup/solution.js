var fs = require('fs')
var _ = require('underscore')

var lineIndex = 0;

fs.readFileSync(process.argv[2]).toString().split('\n').forEach(function(line) { 
  lineIndex++;
  if (lineIndex == 1) {
    return;
  }

  var letters = {};

  for (i=0; i<line.length; i++) {
    var c = line.charAt(i).toLowerCase();
    letters[c] = isNaN(letters[c]) ? 1 : letters[c] + 1;
  }

  console.log ("Case #"+(lineIndex - 1)+": " +
  _.min([
    letters['h'] || 0, 
    letters['a'] || 0, 
    Math.floor((letters['c'] || 0) / 2), 
    letters['k'] || 0,
    letters['e'] || 0,
    letters['r'] || 0,
    letters['u'] || 0,
    letters['p'] || 0
    ])
  );
})