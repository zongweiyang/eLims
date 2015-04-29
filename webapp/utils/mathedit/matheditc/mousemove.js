function mousePosition(ev)

{
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }
    return {
        x:ev.clientX + designDoc.body.scrollLeft - designDoc.body.clientLeft,
        y:ev.clientY + designDoc.body.scrollTop - designDoc.body.clientTop
    };
}


function mouseMove(ev)
{
	//alert("d");
       ev = ev || window.event;
       var mousePos = mousePosition(ev);
       designDoc.getElementById('divs1').setAttribute("style","position:absolute;left:"+(mousePos.x+10)+";top:"+(mousePos.y+10));
}