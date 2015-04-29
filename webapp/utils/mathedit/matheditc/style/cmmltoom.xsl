<?xml version="1.0" encoding="utf-8"?>

<!-- ********************************************************** -->
<!-- XSL Transform of Content MathML to OpenMath                -->
<!--                                                            -->
<!-- Author: Clare M. So <clare@scl.csd.uwo.ca>                 -->
<!--                                                            -->
<!-- July to August 2002                                        -->
<!--                                                            -->
<!-- (Last modified July 9, 2003)                               -->
<!-- ********************************************************** -->



<!-- ********************************************************** -->
<!-- CHANGE LOG                                                 -->
<!-- ********************************************************** -->
<!-- July 9, 2003 - Add templates for diff having degree        -->
<!--                decendent                                   -->




<!-- Special MathML Entities 

<!DOCTYPE stylesheet [
<!ENTITY pi "&#x003C0;">
<!ENTITY e "&#x02147E;">
<!ENTITY ee "&#x02147E;">
<!ENTITY ExponentialE "&#x02147E;">
<!ENTITY ImaginaryI "&#x02148;">
<!ENTITY ii "&#x02148;">
<!ENTITY gamma "&#x003B3;">
<!ENTITY infin "&#x0221E;">
<!ENTITY infty "&#x0221E;">
<!ENTITY true "&#xF0002;">
<!ENTITY false "&#xF0003;">
<!ENTITY NotANumber "&#xF0001;">
<!ENTITY NaN "&#xF0001;">
]>-->


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  
  xmlns:om="http://www.openmath.org/OpenMath"
  xmlns="http://www.openmath.org/OpenMath"
  exclude-result-prefixes=""
  version="1.0">



 <xsl:param name="warn" select="false()"/> 


  <!-- math -->
  <xsl:template match="math">
    <om:OMOBJ>
      <xsl:apply-templates/>
    </om:OMOBJ>
  </xsl:template>


  <!-- mtext -->
  <xsl:template match="mtext">
    <om:OMSTR>
      <xsl:value-of select="normalize-space(.)"/>
    </om:OMSTR>
  </xsl:template>


  <!-- mtext (with definitionURL) -->
  <xsl:template match="mtext[@definitionURL]">
    <om:OMB>
      <xsl:value-of select="normalize-space(.)"/>
    </om:OMB>
  </xsl:template>

 




  <!-- **************************************************** -->
  <!-- ****************** Token Elements ****************** -->  
  <!-- **************************************************** -->

  <!-- cn, ci, csymbol -->

  <!-- ci -->
  <xsl:template match="ci">
    <xsl:choose>
      <xsl:when test="starts-with(name(descendant::*),'m')">
        <xsl:comment>ERROR: OpenMath does not support mixed MathML markup</xsl:comment>
      </xsl:when>
      <xsl:otherwise>
        <om:OMV name="{normalize-space(.)}"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- ci (with type) -->
  <xsl:template match="ci[@type]">
    <om:OMATTR>
      <om:OMATP>
        <om:OMS cd="mathmltypes" name="type"/>
        <om:OMS cd="mathmltypes" name="{concat(translate(normalize-space(@type),'-','_'),'_type')}"/>
      </om:OMATP>
      <om:OMV name="{normalize-space(.)}"/>
     </om:OMATTR>
  </xsl:template>

  <!-- cn (real floating-point, or constant type) -->
  <xsl:template match="cn">
    <xsl:variable name="NUM" select="normalize-space(.)"/>
    <xsl:choose>
      <xsl:when test="$NUM='1'">
        <om:OMS cd="alg1" name="one"/>
      </xsl:when>
      <xsl:when test="$NUM='0'">
        <om:OMS cd="alg1" name="zero"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x003C0;'">
        <om:OMS cd="nums1" name="pi"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02147E;' or $NUM='&#x02147E;'">
        <om:OMS cd="nums1" name="e"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x003B3;'">
        <om:OMS cd="nums1" name="gamma"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x0221E;' or $NUM='&#x0221E;'">
        <om:OMS cd="nums1" name="infinity"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0002;'">
        <om:OMS cd="logic1" name="true"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0003;'">
        <om:OMS cd="logic1" name="false"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0001;' or $NUM='&#xF0001;'">
        <om:OMS cd="nums1" name="NaN"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02148;' or $NUM='&#x02148;'">
        <om:OMS cd="nums1" name="i"/>
      </xsl:when>
      <xsl:when test="not(contains($NUM,'.'))">
        <om:OMI><xsl:value-of select="$NUM"/></om:OMI>
      </xsl:when>
      <xsl:otherwise>
        <om:OMF dec="{normalize-space(.)}"/>
       </xsl:otherwise>
     </xsl:choose>
  </xsl:template>

  <!-- cn (base 16) -->
  <xsl:template match="cn[@base='16']">
    <xsl:variable name="NUM" select="normalize-space(.)"/>
    <xsl:choose>
      <xsl:when test="$NUM='1'">
        <om:OMS cd="alg1" name="one"/>
      </xsl:when>
      <xsl:when test="$NUM='0'">
        <om:OMS cd="alg1" name="zero"/>
      </xsl:when>
      <xsl:otherwise>
        <om:OMF hex="{normalize-space(.)}"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- cn (type e-notation) -->
  <xsl:template match="cn[@type='e-notation']">
    <om:OMA>
      <om:OMS cd="bigfloat1" name="bigfloat"/>
      <xsl:apply-templates select="text()[1]" mode="convert"/>
      <om:OMI>10</om:OMI>
      <xsl:apply-templates select="text()[2]" mode="convert"/>
    </om:OMA>
  </xsl:template>

  <!-- cn (type integer) -->
  <xsl:template match="cn[@type='integer']">
    <xsl:variable name="NUM" select="normalize-space(.)"/>
    <xsl:choose>
      <xsl:when test="$NUM='1'">
        <om:OMS cd="alg1" name="one"/>
      </xsl:when>
      <xsl:when test="$NUM='0'">
        <om:OMS cd="alg1" name="zero"/>
      </xsl:when>
       <xsl:otherwise>
         <om:OMI>
           <xsl:value-of select="$NUM"/>
         </om:OMI>
       </xsl:otherwise>
     </xsl:choose>
  </xsl:template>

  <!-- cn (type integer, base 16) -->
  <xsl:template match="cn[@type='integer' and @base='16']">
    <xsl:variable name="NUM" select="normalize-space(.)"/>
    <om:OMI>
      <xsl:choose>
        <xsl:when test="starts-with($NUM,'-')">
          <xsl:value-of select="concat('-x',substring-after($NUM,'-'))"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat('x',$NUM)"/>
        </xsl:otherwise>
      </xsl:choose>
    </om:OMI>
  </xsl:template>

  <!-- cn (type integer, base NOT 10 or 16) -->
  <xsl:template match="cn[@type='integer' and (@base!=16 and @base)]">
    <om:OMA>
      <om:OMS cd="nums1" name="based_integer"/>
      <om:OMI><xsl:value-of select="@base"/></om:OMI>
      <om:OMSTR><xsl:value-of select="normalize-space(.)"/></om:OMSTR>
    </om:OMA>
  </xsl:template>

  <!-- cn (type rational) -->
  <xsl:template match="cn[@type='rational']">
    <om:OMA>
      <om:OMS cd="nums1" name="rational"/>
      <xsl:apply-templates select="text()[1]" mode="convert"/>
      <xsl:apply-templates select="text()[2]" mode="convert"/>
    </om:OMA>
  </xsl:template>

  <!-- cn (type complex-cartesian) -->
  <xsl:template match="cn[@type='complex-cartesian']">
    <om:OMA>
      <om:OMS cd="complex1" name="complex_cartesian"/>
      <xsl:apply-templates select="text()[1]" mode="convert"/>
      <xsl:apply-templates select="text()[2]" mode="convert"/>
    </om:OMA>
  </xsl:template>

  <!-- cn (type complex-polar) -->
  <xsl:template match="cn[@type='complex-polar']">
    <om:OMA>
      <om:OMS cd="complex1" name="complex_polar"/>
      <xsl:apply-templates select="text()[1]" mode="convert"/>
      <xsl:apply-templates select="text()[2]" mode="convert"/>
    </om:OMA>
  </xsl:template>

  <!-- csymbol -->
  <xsl:template match="csymbol">
    <xsl:choose>
      <xsl:when test="contains(@definitionURL,'openmath')">
        <om:OMS>
          <xsl:attribute name="cd">
            <xsl:value-of select="substring-before(substring-after(normalize-space(@definitionURL),'cd/'),'#')"/>
          </xsl:attribute>
          <xsl:attribute name="name">
            <xsl:value-of select="substring-after(normalize-space(@definitionURL),'#')"/>
          </xsl:attribute>
        </om:OMS>
      </xsl:when>
      <xsl:otherwise>
        <xsl:comment>ERROR: Non OpenMath symbol having the following URL: <xsl:value-of select="@definitionURL"/></xsl:comment>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>





  <!-- **************************************************** -->
  <!-- ************* Basic Content Elements *************** -->  
  <!-- **************************************************** -->

  <!-- apply, reln, fn, interval, inverse, (condition),
       declare, lambda, compose, ident, domain, codomain,
       image, domainofapplication, piecewise, piece, otherwise -->

   <!-- apply or reln (for backwards compatibility) -->
   <xsl:template match="apply|reln">
     <om:OMA>
       <xsl:apply-templates/>
     </om:OMA>
   </xsl:template>

   <!-- fn (for backwards compatibility) -->
   <xsl:template match="fn">
     <xsl:apply-templates/>
   </xsl:template>

   <!-- interval -->
   <xsl:template match="interval">
     <om:OMA>
       <om:OMS cd="interval1">
         <xsl:attribute name="name">
           <xsl:choose>
             <xsl:when test="@closure='open'">interval_oo</xsl:when>
             <xsl:when test="@closure='closed'">interval_cc</xsl:when>
             <xsl:when test="@closure='open-closed'">interval_oc</xsl:when>
             <xsl:when test="@closure='closed-open'">interval_co</xsl:when>
             <xsl:when test="*[1][@type='integer'] and *[2][@type='integer']">integer_interval</xsl:when>
             <xsl:otherwise>interval_cc</xsl:otherwise>
           </xsl:choose>
         </xsl:attribute>
       </om:OMS>    
       <xsl:apply-templates/>
     </om:OMA>
   </xsl:template>

   <!-- inverse -->
   <xsl:template match="inverse">
     <om:OMS cd="fns1" name="inverse"/>
   </xsl:template>

   <!-- condition -->
   <xsl:template match="condition">
     <xsl:comment>ERROR: The use of "condition" by itself is not supported in OpenMath</xsl:comment>
   </xsl:template>

   <!-- declare (need future improvements) -->
   <xsl:template match="declare">
     <xsl:if test="*[2]">
       <om:OMA>
         <om:OMS cd="relation1" name="eq"/>
         <xsl:apply-templates select="*[1]"/>
         <xsl:apply-templates select="*[2]"/>
       </om:OMA>
     </xsl:if>
   </xsl:template>

   <!-- lambda -->
   <xsl:template match="lambda">
     <om:OMBIND>
       <om:OMS cd="fns1" name="lambda"/>
       <om:OMBVAR>
         <xsl:apply-templates select="bvar[position()>0]/ci"/>
       </om:OMBVAR>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMBIND>
   </xsl:template>

   <!-- compose -->
   <xsl:template match="compose">
     <om:OMS cd="fns1" name="left_compose"/>
   </xsl:template>

   <!-- ident -->
   <xsl:template match="ident">
     <om:OMS cd="fns1" name="identity"/>
   </xsl:template>

   <!-- domain -->
   <xsl:template match="domain">
     <om:OMS cd="fns1" name="domain"/>
   </xsl:template>

   <!-- codomain -->
   <xsl:template match="codomain">
     <om:OMS cd="fns1" name="range"/>
   </xsl:template>

   <!-- image -->
   <xsl:template match="image">
     <om:OMS cd="fns1" name="image"/>
   </xsl:template>

   <!-- domainofapplication -->
   <xsl:template match="domainofapplication">
     <om:OMA>
       <om:OMS cd="fns1" name="domainofapplication"/>
       <xsl:apply-templates select="*[1]"/>
     </om:OMA>
   </xsl:template>

   <!-- piecewise, piece, otherwise -->
   <xsl:template match="piecewise|piece|otherwise">
     <om:OMA>
       <om:OMS cd="piece1" name="{name()}"/>
       <xsl:apply-templates/>
     </om:OMA>
   </xsl:template>







  <!-- **************************************************** -->
  <!-- ******** Arithmetic, Algebra, and Logic ************ -->  
  <!-- **************************************************** -->

  <!-- quotient, (exp), factorial, divide, max, min, minus, plus,
       power, rem, times, root, gcd, and, or, xor, not, implies,
       forall, exists, abs, conjugate, arg, real, imaginary,
       lcm, floor, ceiling -->

   <!-- quotient -->
   <xsl:template match="quotient">
     <om:OMS cd="integer1" name="quotient"/>
   </xsl:template>

   <!-- exp -->
   <xsl:template match="exp">
     <om:OMS cd="transc1" name="exp"/>
   </xsl:template>

   <!-- factorial -->
   <xsl:template match="factorial">
     <om:OMS cd="integer1" name="factorial"/>
   </xsl:template>

   <!-- divide, power, times, abs -->
   <xsl:template match="divide|power|times|abs">
     <om:OMS cd="arith1" name="{name()}"/>
   </xsl:template>
 
   <!-- max, min -->
   <xsl:template match="apply[max]|apply[min]">
     <om:OMA>
       <om:OMS cd="minmax1" name="{name(*[1])}"/>
       <xsl:choose>
         <xsl:when test="condition">
           <om:OMA>
             <om:OMS cd="set1" name="suchthat"/>
             <xsl:apply-templates select="condition" mode="getSetname"/>
             <xsl:choose>
               <xsl:when test="*[position()=last()]=condition 
                               or *[position()=last()]=ci">
                 <xsl:apply-templates select="condition/*"/>
               </xsl:when>
               <xsl:otherwise>
                 <om:OMA>
                   <om:OMS cd="logic1" name="and"/>
                   <xsl:apply-templates select="condition/*"/>
                   <xsl:apply-templates select="*[position()=last()]"/>
                 </om:OMA>
               </xsl:otherwise>
             </xsl:choose>
           </om:OMA>
         </xsl:when>
         <xsl:otherwise>
           <om:OMA>
             <om:OMS cd="set1" name="set"/>
             <xsl:apply-templates/>
           </om:OMA>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- plus -->
   <xsl:template match="apply[plus]">
     <xsl:choose>
       <xsl:when test="descendant::set[@type='multiset'] 
                       or descendant::ci[@type='multiset']">
         <om:OMA>
           <om:OMS cd="multiset1" name="union"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </om:OMA>
       </xsl:when>
       <xsl:when test="descendant::set or descendant::ci[@type='set']">
         <om:OMA>       
           <om:OMS cd="set1" name="union"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </om:OMA>
       </xsl:when>
       <xsl:when test="count(child::*)=1">
         <om:OMS cd="alg1" name="zero"/>
       </xsl:when>
       <xsl:otherwise>
         <om:OMA>
           <om:OMS cd="arith1" name="plus"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </om:OMA>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

   <!-- minus -->
   <xsl:template match="apply[minus]">
     <om:OMA>
       <xsl:choose>
         <xsl:when test="descendant::set[@type='multiset'] 
                         or descendant::ci[@type='multiset']">
           <om:OMS cd="multiset1" name="setdiff"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </xsl:when>
         <xsl:when test="descendant::set or descendant::ci[@type='set']">
           <om:OMS cd="set1" name="setdiff"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </xsl:when>
         <xsl:when test="count(child::*)=2">
           <om:OMS cd="arith1" name="unary_minus"/>
           <xsl:apply-templates select="*[2]"/>
         </xsl:when>
         <xsl:otherwise>
           <om:OMS cd="arith1" name="minus"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- gcd -->
   <xsl:template match="gcd">
     <om:OMS cd="arith1" name="gcd"/>
   </xsl:template>

   <!-- lcm -->
   <xsl:template match="apply[lcm]">
     <om:OMS cd="arith1" name="lcm"/>
   </xsl:template>

   <!-- root -->
   <xsl:template match="apply[root]">
     <om:OMA>
       <om:OMS cd="arith1" name="root"/>
       <xsl:choose>
	 <xsl:when test="degree">
	   <xsl:apply-templates select="*[3]"/>
           <xsl:apply-templates select="degree/*"/>
	 </xsl:when>
	 <xsl:otherwise>
           <xsl:apply-templates select="*[2]"/>
           <om:OMI>2</om:OMI>
	 </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- rem -->
   <xsl:template match="rem">
     <om:OMS cd="integer" name="remainder"/>
   </xsl:template>

   <!-- and, or, xor, not, implies -->
   <xsl:template match="and|or|xor|not|implies">
     <om:OMS cd="logic1" name="{name()}"/>
   </xsl:template>

   <!-- forall, exists -->
   <xsl:template match="apply[forall|exists]">
     <om:OMBIND>
       <om:OMS cd="quant1" name="{name(*[1])}"/>
       <om:OMBVAR>
         <xsl:apply-templates select="bvar[position()>0]/*"/>
       </om:OMBVAR>
       <xsl:choose>
         <xsl:when test="condition">
           <om:OMA>
             <om:OMS cd="logic1" name="implies"/>
             <xsl:apply-templates select="condition/*"/>
             <xsl:apply-templates select="*[last()]"/>
           </om:OMA>
         </xsl:when>
         <xsl:otherwise>
           <xsl:apply-templates select="*[last()]"/>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMBIND>
   </xsl:template>

   <!-- conjugate, real, imaginary -->
   <xsl:template match="conjugate|real|imaginary">
     <om:OMS cd="complex1" name="{name()}"/>
   </xsl:template>

   <!-- arg -->
   <xsl:template match="arg">
     <om:OMS cd="complex1" name="argument"/>
   </xsl:template>

   <!-- floor, ceiling -->
   <xsl:template match="floor|ceiling">
     <om:OMS cd="rounding1" name="{name()}"/>
   </xsl:template>








   <!-- **************************************************** -->
   <!-- ********************* Relations ******************** -->  
   <!-- **************************************************** -->

   <!-- eq, neq, gt, lt, geq, leq, equivalent, approx, factorof -->

   <!-- eq, neq, gt, lt, geq, leq -->
   <!-- Note: OpenMath's functions are binary! -->
   <xsl:template match="apply[eq|neq|gt|lt|geq|leq]">
     <om:OMA>
       <xsl:variable name="OP" select="*[1]"/>
       <xsl:choose>
         <xsl:when test="*[4]">
           <om:OMS cd="logic1" name="and"/>
           <xsl:for-each select="*[position()>2]">
             <om:OMA>
               <om:OMS cd="relation1" name="{name($OP)}"/>
               <xsl:apply-templates select="preceding-sibling::*[1]"/>
               <xsl:apply-templates select="."/>
             </om:OMA>
           </xsl:for-each>
         </xsl:when>
         <xsl:otherwise>
           <om:OMS cd="relation1" name="{name($OP)}"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- equivalent -->
   <!-- Note: OpenMath's equivalent is an binary function! -->
   <xsl:template match="apply[equivalent]">
     <om:OMA>
       <xsl:choose>
         <xsl:when test="*[4]">
           <om:OMS cd="logic1" name="and"/>
           <xsl:for-each select="*[position()>2]">
             <om:OMA>
               <om:OMS cd="logic1" name="equivalent"/>
               <xsl:apply-templates select="preceding-sibling::*[1]"/>
               <xsl:apply-templates select="."/>
             </om:OMA>
           </xsl:for-each>
         </xsl:when>
         <xsl:otherwise>
           <om:OMS cd="logic1" name="equivalent"/>
           <xsl:apply-templates select="*[position()>1]"/>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- approx -->
   <xsl:template match="approx">
     <om:OMS cd="relation1" name="approx"/>
   </xsl:template>

   <!-- factorof -->
   <xsl:template match="factorof">
     <om:OMS cd="integer1" name="factorof"/>
   </xsl:template>







   <!-- **************************************************** -->
   <!-- ********** Calculus and Vector Calculus ************ -->  
   <!-- **************************************************** -->

   <!-- int, diff, partialdiff, lowlimit, uplimit, (bvar),
        (degree), divergence, grad, curl, laplacian -->

   <!-- int -->
   <xsl:template match="apply[int]">
     <om:OMA>
       <om:OMS cd="calculus1">
         <xsl:attribute name="name">
           <xsl:choose>
             <xsl:when test="lowlimit|condition|interval|domainofapplication">defint</xsl:when>
             <xsl:otherwise>int</xsl:otherwise>
           </xsl:choose>
         </xsl:attribute>
       </om:OMS>
       <xsl:choose>
         <xsl:when test="lowlimit and uplimit">
           <om:OMA>
             <om:OMS cd="interval1" name="interval"/>
             <xsl:apply-templates select="lowlimit/*"/>
             <xsl:apply-templates select="uplimit/*"/>
           </om:OMA>
         </xsl:when>
         <xsl:when test="interval">
           <xsl:apply-templates select="interval"/>
         </xsl:when>
         <xsl:when test="condition">
           <xsl:choose>
             <xsl:when test="condition/apply/in">
               <om:OMA>
                 <om:OMS cd="fns1" name="domainofapplication"/>
                 <xsl:apply-templates select="condition/apply[in]/*[3]"/>
               </om:OMA>
             </xsl:when>
             <xsl:otherwise>
               <xsl:comment>ERROR: Specification of domain is not supported</xsl:comment>
             </xsl:otherwise>
           </xsl:choose>
         </xsl:when>
         <xsl:when test="domainofapplication">
           <xsl:apply-templates select="domainofapplication"/>
         </xsl:when>
       </xsl:choose>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- diff -->
   <xsl:template match="apply[diff]">
     <om:OMA>
       <om:OMS cd="calculus1" name="diff"/>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- diff (with degree of differentiation -->
   <xsl:template match="apply[diff and bvar[degree]]">
     <om:OMA>
       <om:OMS cd="calculus1" name="nthdiff"/>
       <xsl:apply-templates select="bvar/degree/*"/>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- partialdiff -->
   <!-- (Note: There is no way to indicate degree of differentiation in OM) -->
   <xsl:template match="apply[partialdiff]">
     <om:OMA>
       <om:OMS cd="calculus1" name="partialdiff"/>
       <xsl:choose>
         <xsl:when test="*[2]=list">
           <xsl:apply-templates select="*[2]"/>
         </xsl:when>
         <xsl:when test="bvar">
           <om:OMA>
             <om:OMS cd="list1" name="list"/>
             <xsl:for-each select="bvar">
               <xsl:if test="normalize-space(ci/text())='x'">
                 <om:OMI>1</om:OMI>
               </xsl:if>
               <xsl:if test="normalize-space(ci/text())='y'">
                 <om:OMI>2</om:OMI>
               </xsl:if>
               <xsl:if test="normalize-space(ci/text())='z'">
                 <om:OMI>3</om:OMI>
               </xsl:if>
             </xsl:for-each>
           </om:OMA>
         </xsl:when>
       </xsl:choose>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- divergence, grad, curl -->
   <xsl:template match="apply[divergence|grad|curl]">
     <om:OMA>
       <om:OMS cd="veccalc1" name="{name(child::*[1])}"/>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>  

   <!-- laplacian -->
   <xsl:template match="apply[laplacian]">
     <om:OMA>
       <om:OMS cd="veccalc1" name="Laplacian"/>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>









   <!-- **************************************************** -->
   <!-- **************** Theory of sets ******************** -->  
   <!-- **************************************************** -->

   <!-- set, list, union, intersect, in, notin, subset, prsubset
        notsubset, notprsubset, seetdiff, card, cartesianproduct -->

   <!-- set -->
   <xsl:template match="set">
     <xsl:choose>
       <xsl:when test="condition">
         <om:OMA>
           <om:OMS cd="set1" name="suchthat"/>
           <xsl:apply-templates select="condition" mode="getSetname"/>
           <xsl:choose>
             <xsl:when test="*[position()=last()]=condition 
                             or *[position()=last()]=ci">
               <xsl:apply-templates select="condition/*"/>
             </xsl:when>
             <xsl:otherwise>
               <om:OMA>
                 <om:OMS cd="logic1" name="and"/>
                 <xsl:apply-templates select="condition/*"/>
                 <xsl:apply-templates select="*[position()=last()]"/>
               </om:OMA>
             </xsl:otherwise>
           </xsl:choose>
         </om:OMA>
       </xsl:when>
       <xsl:otherwise>
         <om:OMA>
           <om:OMS cd="set1" name="set"/>
           <xsl:apply-templates/>
         </om:OMA>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

   <!-- set (type multiset) -->
   <xsl:template match="set[@type='multiset']">
     <xsl:choose>
       <xsl:when test="condition">
         <om:OMA>
           <om:OMS cd="set1" name="suchthat"/>
           <xsl:apply-templates select="condition" mode="getSetname"/>
           <xsl:choose>
             <xsl:when test="*[position()=last()]=condition or *[position()=last()]=ci">
               <xsl:apply-templates select="condition/*"/>
             </xsl:when>
             <xsl:otherwise>
               <om:OMA>
                 <om:OMS cd="logic1" name="and"/>
                 <xsl:apply-templates select="condition/*"/>
                 <xsl:apply-templates select="*[position()=last()]"/>
               </om:OMA>
             </xsl:otherwise>
           </xsl:choose>
         </om:OMA>
       </xsl:when>
       <xsl:otherwise>
         <om:OMA>
           <om:OMS cd="multiset1" name="multiset"/>
           <xsl:apply-templates/>
         </om:OMA>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

   <!-- list -->
   <xsl:template match="list">
     <xsl:choose>
       <xsl:when test="condition">
         <om:OMA>
           <om:OMS cd="list1" name="suchthat"/>
           <xsl:apply-templates select="condition" mode="getSetname"/>
           <xsl:choose>
             <xsl:when test="*[position()=last()]=condition or *[position()=last()]=ci">
               <xsl:apply-templates select="condition/*"/>
             </xsl:when>
             <xsl:otherwise>
               <om:OMA>
                 <om:OMS cd="logic1" name="and"/>
                 <xsl:apply-templates select="condition/*"/>
                 <xsl:apply-templates select="*[position()=last()]"/>
               </om:OMA>
             </xsl:otherwise>
           </xsl:choose>
         </om:OMA>
       </xsl:when>
       <xsl:otherwise>
         <om:OMA>
           <om:OMS cd="list1" name="list"/>
           <xsl:apply-templates/>
         </om:OMA>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

   <!-- union, intersect, in, notin, subset, notsubset, notprsubset, 
        setdiff, card, cartesianproduct -->
   <xsl:template match="apply[union|intersect|in|notin
                        |subset|notsubset|notprsubset|setdiff
                        |card|cartesianproduct]">
     <om:OMA>
       <om:OMS>
         <xsl:attribute name="cd">
           <xsl:choose>
             <xsl:when test="descendant::*[@type='multiset']">multiset1</xsl:when>
             <xsl:otherwise>set1</xsl:otherwise>
           </xsl:choose>
         </xsl:attribute>
         <xsl:attribute name="name">
           <xsl:choose>
             <xsl:when test="*[1]=card">size</xsl:when>
             <xsl:when test="*[1]=cartesianproduct">cartesian_product</xsl:when>
             <xsl:otherwise><xsl:value-of select="name(*[1])"/></xsl:otherwise>
           </xsl:choose>
         </xsl:attribute>
       </om:OMS>
       <xsl:apply-templates select="*[position()>1]"/>
     </om:OMA>
   </xsl:template>









   <!-- **************************************************** -->
   <!-- ************** Sequences and Series **************** -->  
   <!-- **************************************************** -->

   <!-- sum, product, limit, (tendsto) -->

   <!-- sum, product -->
   <xsl:template match="apply[sum|product]">
     <om:OMA>
       <om:OMS cd="arith1" name="{name(*[1])}"/>
       <xsl:choose>
         <xsl:when test="lowlimit and uplimit">
           <om:OMA>
             <om:OMS cd="interval1" name="interval"/>
             <xsl:apply-templates select="lowlimit/*"/>
             <xsl:apply-templates select="uplimit/*"/>
           </om:OMA>
         </xsl:when>
         <xsl:when test="interval">
           <xsl:apply-templates select="interval"/>
         </xsl:when>
         <xsl:when test="condition">
           <xsl:choose>
             <xsl:when test="condition/apply/in">
               <om:OMA>
                 <om:OMS cd="fns1" name="domainofapplication"/>
                 <xsl:apply-templates select="condition/apply[in]/*[3]"/>
               </om:OMA>
             </xsl:when>
             <xsl:otherwise>
               <xsl:comment>ERROR: Specification of domain is not supported</xsl:comment>
             </xsl:otherwise>
           </xsl:choose>
         </xsl:when>
         <xsl:when test="domainofapplication">
           <xsl:apply-templates select="domainofapplication"/>
         </xsl:when>
       </xsl:choose>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- limit -->
   <xsl:template match="apply[limit]">
     <om:OMA>
       <om:OMS cd="limit1" name="limit"/>
       <xsl:choose>
         <xsl:when test="condition">
           <xsl:variable name="type" 
             select="condition/apply/tendsto/@type"/>
           <xsl:apply-templates 
             select="condition/apply[tendsto]/*[last()]"/>
           <om:OMS cd="limit1">
             <xsl:attribute name="name">
               <xsl:choose>
                 <xsl:when test="$type='above'">above</xsl:when>
                 <xsl:when test="$type='below'">below</xsl:when>
                 <xsl:when test="$type='all'">both_sides</xsl:when> 
                 <xsl:otherwise>null</xsl:otherwise> <!-- default -->
               </xsl:choose>
             </xsl:attribute>
           </om:OMS>
         </xsl:when>
         <xsl:when test="lowlimit">
           <xsl:apply-templates select="lowlimit/*"/>
           <om:OMS cd="limit1" name="null"/>
         </xsl:when>
         <xsl:when test="uplimit">
           <xsl:apply-templates select="uplimit/*"/>
           <om:OMS cd="limit1" name="null"/>
         </xsl:when>
       </xsl:choose>
       <xsl:apply-templates select="*[last()]"/>
     </om:OMA>
   </xsl:template>

   <!-- tendsto -->
   <!-- (no equivalent in OpenMath) -->
   <xsl:template match="apply[tendsto]">
     <xsl:comment>ERROR: "Tendsto" is not supported in OpenMath</xsl:comment>
   </xsl:template>




   <!-- **************************************************** -->
   <!-- ********** Elementary Classical Functions ********** -->  
   <!-- **************************************************** -->

   <!-- All trig functions, exp, ln, log -->

   <!-- exp (in Arithmatic, Algebra and Logic section) -->

   <!-- ln -->
   <xsl:template match="ln">
     <om:OMS cd="transc1" name="ln"/>
   </xsl:template>

   <!-- log -->
   <xsl:template match="apply[log]">
     <om:OMA>
       <om:OMS cd="transc1" name="log"/>
       <xsl:choose>
         <xsl:when test="logbase">
           <xsl:apply-templates select="logbase/*"/>
           <xsl:apply-templates select="*[3]"/>
         </xsl:when>
         <xsl:otherwise>
           <om:OMI>10</om:OMI>
           <xsl:apply-templates select="*[2]"/>
         </xsl:otherwise>
       </xsl:choose>
     </om:OMA>
   </xsl:template>

   <!-- (all trig functions) -->
   <xsl:template match="sin|cos|tan|sec|sec
                        |csc|cot|sinh|cosh|tanh
                        |sech|sech|csch|coth|arcsin
                        |arccos|arctan|arcsec|arcsec
                        |arccsc|arccot|arcsinh|arccosh
                        |arctanh|arcsech|arcsech|arccsch
                        |arccoth">
     <om:OMS cd="transc1" name="{name()}"/>
   </xsl:template>






   <!-- **************************************************** -->
   <!-- ******************* Statistics ********************* -->  
   <!-- **************************************************** -->

   <!-- mean, sdev, variance, median, mode, moment, (momentabout) -->

   <!-- mean, sdev, variance -->
   <xsl:template match="apply[mean|sdev|variance]">
     <om:OMA>
       <om:OMS>
	 <xsl:attribute name="cd">
	   <xsl:choose>
	     <xsl:when test="*[3]">s_data1</xsl:when>
	     <xsl:otherwise>s_dist1</xsl:otherwise>
	   </xsl:choose>
	 </xsl:attribute>
	 <xsl:attribute name="name">
           <xsl:value-of select="name(*[1])"/>
	 </xsl:attribute>
       </om:OMS>
       <xsl:apply-templates select="*[position()>1]"/>
     </om:OMA>
   </xsl:template>

   <!-- mode -->
   <xsl:template match="mode">
     <om:OMS cd="s_data1" name="mode"/>
   </xsl:template>

   <!-- median -->
   <xsl:template match="median">
     <om:OMS cd="s_data1" name="median"/>
   </xsl:template>

   <!-- moment, momentabout -->
   <xsl:template match="apply[moment]">
     <om:OMA>
       <xsl:choose>
         <xsl:when test="*[5]">
           <om:OMS cd="s_data1" name="moment"/>
         </xsl:when>
         <xsl:otherwise>
           <om:OMS cd="s_dist1" name="moment"/>
         </xsl:otherwise>
       </xsl:choose>
       <xsl:apply-templates select="degree/*"/>
       <xsl:apply-templates select="momentabout/*"/>
       <xsl:apply-templates select="*[position()>3]"/>
     </om:OMA>
   </xsl:template>





   <!-- **************************************************** -->
   <!-- ******************** Linear Algebra **************** -->  
   <!-- **************************************************** -->

   <!-- vector, matrix, matrixrow, determinant, transpose, 
        selector, vectorproduct, scalarproduct, outerproduct -->

   <!-- vector -->
   <xsl:template match="vector">
     <om:OMA>
       <om:OMS cd="linalg3" name="vector"/>
       <xsl:apply-templates/>
     </om:OMA>
   </xsl:template>

   <!-- matrix, matrixrow -->
   <xsl:template match="matrix|matrixrow">
     <om:OMA>
       <om:OMS cd="linalg2" name="{name()}"/>
       <xsl:apply-templates/>
     </om:OMA>
   </xsl:template>


   <!-- determinant, transpose -->
   <xsl:template match="determinant|transpose">
     <om:OMS cd="linalg1" name="{name()}"/>
   </xsl:template>

   <!-- selector -->
   <xsl:template match="apply[selector]">
     <xsl:choose>
       <xsl:when test="count(child::*) &lt; 3">
         <xsl:comment>ERROR: No arguement given</xsl:comment>
       </xsl:when>
       <xsl:when test="*[2]=list or *[2]=ci[@type='list']">
         <xsl:comment>ERROR: OpenMath does not support list selector</xsl:comment>
       </xsl:when>
       <xsl:when test="(*[2]=matrix or *[2]=ci[@type='matrix']) and count(child::*)!=4">
         <xsl:comment>ERROR: OpenMath's matrix selector cannot select a row of a matrix</xsl:comment>
       </xsl:when>
       <xsl:when test="(*[2]=vector or *[2]=matrixrow 
                       or *[2]=ci[@type='vector' or @type='matrixrow']) and count(child::*)=4">
         <xsl:comment>ERROR: Too many arguments for vector or matrixrow</xsl:comment>
       </xsl:when>
       <xsl:otherwise>
         <om:OMA>
           <om:OMS cd="linalg1">
             <xsl:attribute name="name">
               <xsl:choose>
                 <xsl:when test="count(child::*)=4">matrix_selector</xsl:when> <!-- matrix -->
                 <xsl:when test="count(child::*)=3">vector_selector</xsl:when> <!-- vector or matrixrow -->
                 <xsl:otherwise>vector_selector</xsl:otherwise>
               </xsl:choose>
             </xsl:attribute>
           </om:OMS>
           <xsl:choose>
             <xsl:when test="count(child::*)=3"> <!-- vector or matrixrow -->
               <xsl:apply-templates select="*[3]"/>
               <xsl:apply-templates select="*[2]"/>
             </xsl:when>
             <xsl:when test="count(child::*)=4"> <!-- matrix -->
               <xsl:apply-templates select="*[4]"/>
               <xsl:apply-templates select="*[3]"/>
               <xsl:apply-templates select="*[2]"/>
             </xsl:when>
           </xsl:choose>
         </om:OMA>
       </xsl:otherwise>
     </xsl:choose>
    </xsl:template>
          
   <!-- vectorproduct -->
   <xsl:template match="vectorproduct">
     <om:OMS cd="linalg1" name="vector_product"/>
   </xsl:template>

   <!-- scalarproduct -->
   <xsl:template match="scalarproduct">
     <om:OMS cd="linalg1" name="scalar_product"/>
   </xsl:template>

   <!-- outerproduct -->
   <xsl:template match="outerproduct">
     <om:OMS cd="linalg1" name="outer_product"/>
   </xsl:template>






   <!-- **************************************************** -->
   <!-- ************ Semantic mapping elements ************* -->  
   <!-- **************************************************** -->

   <!-- semantics, annotation, annotation-xml -->

   <!-- semantics -->
   <xsl:template match="semantics">
     <om:OMATTR>
       <om:OMATP>
         <xsl:apply-templates select="annotation"/>
         <xsl:apply-templates select="annotation-xml"/>
       </om:OMATP>
       <xsl:apply-templates select="*[1]"/>
     </om:OMATTR>
   </xsl:template>

   <!-- annotation, annotation-xml -->
   <!-- (OpenMath does not support other encodings such as Maple and Mathematica) -->
   <xsl:template match="annotation|annotation-xml">
     <xsl:choose>
       <xsl:when test="contains(@encoding,'MathML')">
         <om:OMS cd="altenc" name="MathML_encoding"/>
         <OMXML><xsl:value-of select="normalize-space(.)"/></OMXML>
       </xsl:when>
       <xsl:when test="contains(@encoding,'LaTeX')">
         <om:OMS cd="altenc" name="LaTeX_encoding"/>
         <om:OMSTR><xsl:value-of select="normalize-space(.)"/></om:OMSTR>
       </xsl:when>
       <xsl:otherwise>
         <xsl:comment>OpenMath does not support other encodings</xsl:comment>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>






   <!-- **************************************************** -->
   <!-- ************ Constants and symbol elements ********* -->  
   <!-- **************************************************** -->
 
   <!-- integers, reals, rationals, naturalnumbers, complexes, primes
        exponentiale, imaginaryi, notanumber, true, fasle, emptyset,
        pi, eulergamma, infinity -->

   <!-- integers -->
   <xsl:template match="integers">
     <om:OMS cd="setname1" name="Z"/>
   </xsl:template>

   <!-- reals -->
   <xsl:template match="reals">
     <om:OMS cd="setname1" name="R"/>
   </xsl:template>

   <!-- rationals -->
   <xsl:template match="rationals">
     <om:OMS cd="setname1" name="Q"/>
   </xsl:template>

   <!-- naturalnumbers -->
   <xsl:template match="naturalnumbers">
     <om:OMS cd="setname1" name="N"/>
   </xsl:template>

   <!-- complexes -->
   <xsl:template match="complexes">
     <om:OMS cd="setname1" name="C"/>
   </xsl:template>

   <!-- primes -->
   <xsl:template match="primes">
     <om:OMS cd="setname1" name="P"/>
   </xsl:template>

   <!-- exponentiale -->
   <xsl:template match="exponentiale">
     <om:OMS cd="nums1" name="e"/>
   </xsl:template>

   <!-- imaginaryi -->
   <xsl:template match="imaginaryi">
     <om:OMS cd="nums1" name="i"/>
   </xsl:template>

   <!-- notanumber -->
   <xsl:template match="notanumber">
     <om:OMS cd="nums1" name="NaN"/>
   </xsl:template>

   <!-- true -->
   <xsl:template match="true">
     <om:OMS cd="logic1" name="true"/>
   </xsl:template>

   <!-- false -->
   <xsl:template match="false">
     <om:OMS cd="logic1" name="false"/>
   </xsl:template>

   <!-- emptyset -->
   <xsl:template match="emptyset">
     <xsl:choose>
       <xsl:when test="parent::*/descendant::*[@type='multiset']">
         <om:OMS cd="multiset1" name="emptyset"/>
       </xsl:when>
       <xsl:otherwise>
         <om:OMS cd="set1" name="emptyset"/>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

   <!-- pi -->
   <xsl:template match="pi">
     <om:OMS cd="nums1" name="pi"/>
   </xsl:template>
   
   <!-- eulergamma -->
   <xsl:template match="eulergamma">
     <om:OMS cd="nums1" name="gamma"/>
   </xsl:template>

   <!-- infinity -->
   <xsl:template match="infinity">
     <om:OMS cd="nums1" name="infinity"/>
   </xsl:template>





 
   <!-- **************************************************** -->
   <!-- ***************** Helper functions ***************** -->  
   <!-- **************************************************** -->

  <!-- This template is for converting from text children in rational or complex numbers to actual om:OMIs, OMFs, or om:OMSs -->
  <xsl:template match="text()" mode="convert">
    <xsl:variable name="NUM" select="normalize-space(.)"/>
    <xsl:choose>
      <xsl:when test="$NUM='1'">
        <om:OMS cd="alg1" name="one"/>
      </xsl:when>
      <xsl:when test="$NUM='0'">
        <om:OMS cd="alg1" name="zero"/>
      </xsl:when>
      <xsl:when test="NUM='&#x003C0;'">
        <om:OMS cd="nums1" name="pi"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02147E;'">
        <om:OMS cd="nums1" name="e"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02147E;'">
        <om:OMS cd="nums1" name="e"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x003B3;'">
        <om:OMS cd="nums1" name="gamma"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x0221E;'">
        <om:OMS cd="nums1" name="infinity"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x0221E;'">
        <om:OMS cd="nums1" name="infinity"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0002;'">
        <om:OMS cd="logic1" name="true"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0003;'">
        <om:OMS cd="logic1" name="false"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0001;'">
        <om:OMS cd="nums1" name="NaN"/>
      </xsl:when>
      <xsl:when test="$NUM='&#xF0001;'">
        <om:OMS cd="nums1" name="NaN"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02148;'">
        <om:OMS cd="nums1" name="i"/>
      </xsl:when>
      <xsl:when test="$NUM='&#x02148;'">
        <om:OMS cd="nums1" name="i"/>
      </xsl:when>
      <xsl:when test="contains($NUM,'.')">
        <om:OMF dec="{$NUM}"/>
      </xsl:when>
      <xsl:otherwise>
        <om:OMI><xsl:value-of select="$NUM"/></om:OMI>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>


   <xsl:template match="condition" mode="getLimit">
     <xsl:variable name="type" select="tendsto/@type"/>
     <xsl:apply-templates select="apply[tendsto]/*[last()]"/>
     <om:OMS cd="limit1">
       <xsl:attribute name="name">
         <xsl:choose>
           <xsl:when test="$type='above'">above</xsl:when>
           <xsl:when test="$type='below'">below</xsl:when>
           <xsl:when test="$type='all'">both_sides</xsl:when> 
           <xsl:otherwise>null</xsl:otherwise> <!-- default -->
         </xsl:choose>
       </xsl:attribute>
     </om:OMS>
   </xsl:template>


   <xsl:template match="condition" mode="getSetname">
     <xsl:choose>
       <xsl:when test="descendant::apply/in">
         <xsl:apply-templates select="descendant::apply[in][position()=1]/*[3]"/>
       </xsl:when>
       <xsl:when test="normalize-space(descendant::ci/text())=i or
                       normalize-space(descendant::ci/text())=j or
                       normalize-space(descendant::ci/text())=k or
                       normalize-space(descendant::ci/text())=l or
                       normalize-space(descendant::ci/text())=m or
                       normalize-space(descendant::ci/text())=n or
                       normalize-space(descendant::ci/text())=I or
                       normalize-space(descendant::ci/text())=J or
                       normalize-space(descendant::ci/text())=K or
                       normalize-space(descendant::ci/text())=L or
                       normalize-space(descendant::ci/text())=M or
                       normalize-space(descendant::ci/text())=N">
         <om:OMS cd="setname1" name="N"/>
       </xsl:when>
       <xsl:when test="normalize-space(descendant::ci/text())=z or
                       normalize-space(descendant::ci/text())=w">
         <om:OMS cd="setname1" name="C"/>
       </xsl:when>
       <xsl:otherwise>
         <om:OMS cd="setname1" name="R"/>
       </xsl:otherwise>
     </xsl:choose>
   </xsl:template>

</xsl:stylesheet>



