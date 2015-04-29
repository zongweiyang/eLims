

var str='<object id="mathedit" classid="clsid:32F66A20-7614-11D4-BD11-00104BD3F987" codebase="http://www.dessci.com/dl/mathplayer.cab"></object>';
str=str+'<?import namespace="mml" implementation="#mathedit"?>';
document.write(str);


function checkForMathPlayer()
{
  // first test platform, as the MathPlayer-specific code only works in IE
  if( isIEWindows())
  {
    // check browser version since MathPlayer 2 requires IE 6
    if (ieVersion() >= 6.0)
    {
      if (isMPInstalled())
      {
        var start = navigator.appVersion.indexOf("MathPlayer");
        if (start != -1)
        {
          // notify reader their browser is properly set up
          return 1;
        }
        else
        {
          // notify reader they need to upgrade to MathPlayer 2
          return 2;
        }
      }
      else
      {
        // direct reader to MathPlayer installation page
        return 3;
      }
    }
    else
    {
      // notify reader they need to upgrade IE
      return 4;
    }
  }
  else
  {
    // direct reader to information about other browsers
    return 5;
  }
}

// returns True if MathPlayer is installed
function isMPInstalled()
{
  try
  {
    var oMP = new ActiveXObject("MathPlayer.Factory.1");
    return true;
  }
  catch(e)
  {
    return false;
  }
}

// returns True if running on any version of IE Windows
function isIEWindows()
{
  return( (navigator.appName=="Microsoft Internet Explorer") &&
    (navigator.appVersion.indexOf("Windows") != -1) );
}

// returns version of Internet Explorer
function ieVersion()
{
  var ieVer = 0;
  var start = navigator.appVersion.indexOf("MSIE ");
  if (start != -1) {
    ieVer = parseFloat(navigator.appVersion.substring(start+5));
  }
  return ieVer;
}
