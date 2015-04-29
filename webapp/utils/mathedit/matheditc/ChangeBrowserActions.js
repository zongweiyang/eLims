/**
    Author:Zhang Liao
    Description:This file is defined to prevent the default actions of the browsers from conflicting with MathEdit.
*/

function PreventDefaultActions(e)
{
    var evt = window.event?window.event:e;
    if(evt.ctrlKey == 1)
    {
       // alert("1 "+evt.keyCode);
        if(
           evt.keyCode == 65|| //ctrl+a
           evt.keyCode == 70|| //ctrl+f
           evt.keyCode == 71|| //ctrl+g
           evt.keyCode == 72|| //ctrl+h
           evt.keyCode == 75|| //ctrl+k
           evt.keyCode == 76|| //ctrl+l
           evt.keyCode == 78|| //ctrl+n
           evt.keyCode == 79|| //ctrl+o
           evt.keyCode == 80|| //ctrl+p
           evt.keyCode == 82|| //ctrl+r
           evt.keyCode == 83|| //ctrl+s
           evt.keyCode == 84|| //ctrl+t
           evt.keyCode == 87 //ctrl+w
          )
          {
            return false;
          }
   }
   if(evt.altKey == 1)
   {
    //alert("2 "+evt.keyCode);
        if(
            evt.keyCode == 65|| //alt+a
            evt.keyCode == 66|| //alt+b
            evt.keyCode == 67|| //alt+c
            evt.keyCode == 68|| //alt+d
            evt.keyCode == 69|| //alt+e
            evt.keyCode == 70|| //alt+f
            evt.keyCode == 72|| //alt+h
            evt.keyCode == 84|| //alt+t
            evt.keyCode == 86|| //alt+v
            evt.keyCode == 90 //alt+z
            )
            {
                return false;
            }
   }
   if(evt.keyCode == 9)
   {
        return false;
   }

    if(getNavigatorType() == 1)
    {
        event.returnValue = false;
    }



}
