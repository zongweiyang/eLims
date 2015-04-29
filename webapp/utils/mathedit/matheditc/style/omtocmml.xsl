<?xml version="1.0" encoding="utf-8"?>

<!-- ********************************************************** -->
<!-- XSL Transform of OpenMath to Content MathML                -->
<!-- (Based on initial version by David Carlisle)               -->
<!--                                                            -->
<!-- Author: Clare M. So <clare@scl.csd.uwo.ca>                 -->
<!--                                                            -->
<!-- May to August 2002                                         -->
<!--                                                            -->
<!-- (Last updated July 9, 2003)                                --> 
<!-- ********************************************************** -->



<!-- ********************************************************** -->
<!-- CHANGE LOG                                                 -->
<!-- ********************************************************** -->
<!-- May 13, 2003 - Add template nthdiff of calculus1 CD        -->
<!-- May 14, 2003 - Add templates for moreerrors CD             -->
<!-- May 15, 2003 - Split templates for multiset1, set1,        --> 
<!--                and list1 CDs                               -->
<!--                Split templates for s_dist1 and s_data1 CDs -->
<!-- June 4, 2003 - Fix bugs in splitting set1, multiset1, and  -->
<!--                set1 CDs                                    -->
<!--              - Add templates for transc3 CD                -->
<!-- July 9, 2003 - Add template for nthdiff                    -->


<!-- Special MathML entities 
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


<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:om="http://www.openmath.org/OpenMath"
  xmlns:mml="http://www.w3.org/1998/Math/MathML"
  xmlns="http://www.w3.org/1998/Math/MathML"
  exclude-result-prefixes="om"
  version="1.0">
   <xsl:param name="warn" select="false()"/>  
  <!--  
  <xsl:output method="text" indent="no"/>
-->
  <xsl:strip-space elements="*"/>

  <xsl:variable name="defaultOMSpriority">-10</xsl:variable>





 
  <!-- **************************************************** -->
  <!-- ****************** Basic Elements ****************** -->  
  <!-- **************************************************** -->
   

  <!-- OMOBJ (D. Carlisle) -->
  <xsl:template match="om:OMOBJ">
    <mml:math>
      <xsl:apply-templates/>
    </mml:math>
  </xsl:template>
  
  
  <!-- OMI (D. Carlisle) -->
  <xsl:template match="om:OMI">
    <mml:cn type="integer">
      <xsl:variable name="x" select="normalize-space(.)"/>
      <xsl:choose>
        <xsl:when test="contains($x,'x')">
          <xsl:attribute name="base">16</xsl:attribute>
          <xsl:value-of select="concat(substring-before($x,'x'),substring-after($x,'x'))"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$x"/> <!-- default is decimal -->
        </xsl:otherwise>
      </xsl:choose>
    </mml:cn>
  </xsl:template>
  
  
  <!-- OMV (D. Carlisle) -->
  <xsl:template match="om:OMV">
    <mml:ci>
      <xsl:value-of select="normalize-space(@name)"/>
    </mml:ci>
  </xsl:template>
  
  
  <!-- OMF (decimal) -->  
  <xsl:template match="om:OMF[@dec]">
    <mml:cn>
      <xsl:value-of select="normalize-space(@dec)"/>
    </mml:cn>
  </xsl:template>
  

  <!-- OMF (hex) -->
  <xsl:template match="om:OMF[@hex]">
    <mml:cn base="16">
      <xsl:value-of select="normalize-space(@hex)"/>
    </mml:cn>
  </xsl:template>


  <!-- OMA (D. Carlisle) -->
  <xsl:template match="om:OMA">
    <mml:apply>
      <xsl:apply-templates/>
    </mml:apply>
  </xsl:template>
  

  <!-- OMB -->
  <!-- Note: No Content MathML equivalent -->
  <xsl:template match="om:OMB">
    <mtext definitionURL="http://www.openmath.org/objects#OMB">
      <xsl:value-of select="."/>
    </mtext>
  </xsl:template>


  <!-- OMSTR (D. Carlisle) -->
  <!-- Note: mtext is a presentational MathML tag -->
  <xsl:template match="om:OMSTR">
    <mtext>
      <xsl:value-of select="."/>
    </mtext>
  </xsl:template>






  <!-- ***************************************************** -->
  <!-- ******************  MathML group ******************** -->
  <!-- ***************************************************** -->


  <!-- Content Dicitionary: alg1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~ -->  

  <!-- This CD contains: zero, one -->

  <!-- Trivial cases: none -->
  <xsl:template match="om:OMS[@cd='alg1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- zero -->
  <xsl:template match="om:OMS[@cd='alg1' and @name='zero']">
    <mml:cn type="integer">0</mml:cn>
  </xsl:template>

  <!-- one -->
  <xsl:template match="om:OMS[@cd='alg1' and @name='one']">
    <mml:cn type="integer">1</mml:cn>         
  </xsl:template>

 



  <!-- Content Dictionary: arith1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD conatains: abs, divide, gcd, lcm, minus, plus, power, product, 
       root, sum, unary_minus -->

  <!-- Trivial Cases: abs, divide, gcd, lcm, minus, plus -->
  <xsl:template match="om:OMS[@cd='arith1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template> 
 
  <!-- unary_minus -->  
  <xsl:template match="om:OMS[@cd='arith1' and @name='unary_minus']">
    <mml:minus/>
  </xsl:template>

  <!-- root -->
  <xsl:template match="om:OMA[om:OMS[@cd='arith1' and @name='root']]">
    <mml:apply>
      <mml:root/>
      <mml:degree>
        <xsl:apply-templates select="*[3]"/>
      </mml:degree>
      <xsl:apply-templates select="*[2]"/>
    </mml:apply>
  </xsl:template>

  <!-- sum and product -->
  <xsl:template match="om:OMA[om:OMS[@cd='arith1' and (@name='sum' or @name='product')]]">
    <mml:apply>
      <xsl:element name="mml:{om:OMS[1]/@name}"/>
      <mml:bvar>
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="3"/> <!-- the bounded var is in the func -->
        </xsl:apply-templates>
      </mml:bvar>
      <xsl:apply-templates select="*[2]"/> <!-- range of product/summation -->
      <xsl:apply-templates select="*[3]"/>
    </mml:apply>
  </xsl:template>
  





  <!-- Content Dictionary: bigfloat1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
  
  <!-- This CD contains: bigfloat, bigfloatprec -->

  <!-- Trivial cases: none -->

  <!-- bigfloat -->
  <xsl:template match="om:OMA[om:OMS[@cd='bigfloat1' and @name='bigfloat']]">
    <mml:apply>
      <mml:times/>
      <xsl:apply-templates select="*[2]"/>
      <mml:apply>
        <mml:power/>
        <xsl:apply-templates select="*[3]"/>
        <xsl:apply-templates select="*[4]"/>
      </mml:apply>
    </mml:apply>
  </xsl:template>

  <!-- bigfloatprec -->
  <!-- Note: No Content MathML equivalent -->
  <xsl:template match="om:OMS[@cd='bigfloat1' and @name='bigfloatprec']">
    <csymbol encoding="OpenMath" 
      definitionURL="http://www.openmath.org/cd/bigfloat1#bigfloatprec"/>
  </xsl:template>  
  




 
  <!-- Content Dictionary: calculus1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: defint, diff, int, nthdiff, partialdiff -->

  <!-- Trivial cases: partialdiff -->
  <xsl:template match="om:OMS[@cd='calculus1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- diff, int -->
  <xsl:template match="om:OMA[om:OMS[@cd='calculus1' and (@name='diff' or @name='int')]]">
    <mml:apply>
      <xsl:element name="mml:{om:OMS[1]/@name}"/>
      <mml:bvar>
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="2"/>
        </xsl:apply-templates>
      </mml:bvar>
      <xsl:apply-templates select="*[2]"/>
    </mml:apply>
  </xsl:template>
  
  <!-- defint -->
  <xsl:template match="om:OMA[om:OMS[@cd='calculus1' and @name='defint']]">
    <mml:apply>
      <mml:int/> <!-- pretty much the same as sum and product... CHECK domainofapp -->
      <mml:bvar> <!-- perphaps write a method for the similar parts... -->
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="3"/>
        </xsl:apply-templates>
      </mml:bvar>
      <xsl:apply-templates select="*[2]"/> <!-- range of diff -->
      <xsl:apply-templates select="*[3]"/>
    </mml:apply>
  </xsl:template>
  
  <!-- nthdiff -->
  <xsl:template match="om:OMA[om:OMS[@cd='calculus1' and @name='nthdiff']]">
    <mml:apply>
      <mml:diff/>
      <mml:bvar>
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="3"/>
        </xsl:apply-templates>
        <xsl:apply-templates select="*[2]"/>
      </mml:bvar>
      <xsl:apply-templates select="*[3]"/>
    </mml:apply>
  </xsl:template>
  



  <!-- Content Dictionaries: complex1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
  
  <!-- This CD contains: argument, complex_cartesian, complex_polar, conjugate,
       imaginary, real -->

  <!-- Trivial cases: conjugate, imaginary, real -->
  <xsl:template match="om:OMS[@cd='complex1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- argument -->
  <xsl:template match="om:OMS[@cd='complex1' and @name='argument']">
    <mml:arg/>
  </xsl:template>

  <!-- complex_cartesian or complex_polar -->
  <xsl:template match="om:OMA[om:OMS[@cd='complex1' and (@name='complex_cartesian' or @name='complex_polar')]]">
    <xsl:variable name="type_name" select="translate(om:OMS[1]/@name,'_','-')"/>
    <xsl:choose>
      <xsl:when test="child::om:OMV or child::om:OMA">
        <mml:apply>
          <csymbol definitionURL="{concat('http://www.openmath.org/cd/complex1#',om:OMS[1]/@name)}"/>
          <xsl:apply-templates select="*[2]"/>
          <xsl:apply-templates select="*[3]"/>
        </mml:apply>
      </xsl:when>
      <xsl:otherwise>
	<mml:cn type="{$type_name}">
          <xsl:apply-templates select="*[2]" mode="convert"/>
	  <mml:sep/>
          <xsl:apply-templates select="*[3]" mode="convert"/>
	</mml:cn>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>





  

  
  <!-- Content Dictionary: fns1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: domain, domainofapplication, identity, image, inverse,
       lambda, left_compose, left_inverse, right_inverse -->

  <!-- Trivial cases: domain, image, inverse -->
  <xsl:template match="om:OMS[@cd='fns1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- domainofapplication -->
  <xsl:template match="om:OMA[om:OMS[@cd='fns1' and @name='domainofapplication']]">
    <mml:domainofapplication>
      <xsl:apply-templates select="*[2]"/>
    </mml:domainofapplication>
  </xsl:template>

  <!-- identity -->
  <xsl:template match="om:OMS[@cd='fns1' and @name='identity']">
    <mml:ident/>
  </xsl:template>

  <!-- lambda -->
  <xsl:template match="om:OMBIND[om:OMS[@cd='fns1' and @name='lambda']]">
    <mml:lambda>
      <xsl:for-each select="om:OMBVAR/child::om:OMV">
        <mml:bvar>
          <xsl:apply-templates select="."/>
        </mml:bvar>
      </xsl:for-each>
      <xsl:apply-templates select="*[3]"/>
    </mml:lambda>
  </xsl:template>

  <!-- range -->
  <xsl:template match="om:OMS[@cd='fns1' and @name='range']">
    <mml:codomain/>
  </xsl:template>
  
  <!-- left_compose -->
  <xsl:template match="om:OMS[@cd='fns1' and @name='left_compose']">
    <mml:compose/>
  </xsl:template>

  <!-- left_inverse -->
  <xsl:template match="om:OMS[@cd='fns1' and @name='left_inverse']">
    <mml:inverse/>
  </xsl:template>

  <!-- right_inverse -->
  <!-- Note: No Content MathML equivalent -->
  <xsl:template match="om:OMS[@cd='fns1' and @name='right_inverse']">
    <mml:inverse encoding="OpenMath" definitionURL="http://www.openmath.org/cd/fns1#right_inverse"/>
  </xsl:template>





  <!-- Content Dictionary: integer1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: factorial, factorof, quotient, remainder -->

  <!-- Trivial cases: factorof, factorial, quotient -->
  <xsl:template match="om:OMS[@cd='integer1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>
  
  <!-- remainder -->
  <xsl:template match="om:OMS[@cd='integer1' and @name='remainder']">
    <mml:rem/>
  </xsl:template>





  <!-- Content Dictionary: interval1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: integer_interval, interval, interval_oo, interval_cc,
       interval_oc, interval_co -->

  <!-- Trivial case: none -->

  <!-- (All) -->
  <xsl:template match="om:OMA[om:OMS[@cd='interval1']]">
    <mml:interval>
      <xsl:choose>
	<xsl:when test="om:OMS[1]/@name='interval_oo'">
          <xsl:attribute name='closure'>open</xsl:attribute>
        </xsl:when>
	<xsl:when test="om:OMS[1]/@name='interval_cc'">
          <xsl:attribute name='closure'>closed</xsl:attribute>
        </xsl:when>
	<xsl:when test="om:OMS[1]/@name='interval_oc'">
          <xsl:attribute name='closure'>open-closed</xsl:attribute>
        </xsl:when>
	<xsl:when test="om:OMS[1]/@name='interval_co'">
          <xsl:attribute name='closure'>closed-open</xsl:attribute>
        </xsl:when>
      </xsl:choose>
      <xsl:apply-templates select="*[2]"/>
      <xsl:apply-templates select="*[3]"/>
    </mml:interval>
  </xsl:template>





  <!-- Content Dictionary: linalg1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: determinant, matrix_selector, outerproduct,
       scalarproduct, transpose, vector_selector, vector_product -->

  <!-- Trivial cases: determinant, outerproduct, scalarproduct, transpose, 
       vectorproduct -->
  <xsl:template match="om:OMS[@cd='linalg1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>
 
  <!-- vector_selector -->
  <xsl:template match="om:OMA[om:OMS[@cd='linalg1' and @name='vector_selector']]">
    <mml:apply>
      <mml:selector/>
      <xsl:apply-templates select="*[3]"/> <!-- the vector -->
      <xsl:apply-templates select="*[2]"/>
    </mml:apply>
  </xsl:template>


  <!-- matrix_selector -->
  <xsl:template match="om:OMA[om:OMS[@cd='linalg1' and @name='matrix_selector']]">
    <mml:apply>
      <mml:selector/>
      <xsl:apply-templates select="*[4]"/> <!-- the matrix -->
      <xsl:apply-templates select="*[3]"/> 
      <xsl:apply-templates select="*[2]"/>
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: linalg2 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: matrix, matrixrow, vector -->

  <!-- Trivial cases: none -->

  <!-- matrixrow, matrix -->
  <xsl:template match="om:OMA[om:OMS[@cd='linalg2']]">
    <xsl:element name="mml:{om:OMS[1]/@name}">
      <xsl:apply-templates select="*[position()>1]"/>
    </xsl:element>
  </xsl:template>

  <!-- (row) vector -->
  <xsl:template match="om:OMA[om:OMS[@cd='linalg2' and @name='vector']]">
    <mml:apply>
      <transpose/>
      <vector>
        <xsl:apply-templates select="*[position()>1]"/>
      </vector>
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: limit1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: above, below, bothsides, limit, null -->

  <!-- Trivial cases: none -->

  <!-- both_sides, above, below, null -->
  <xsl:template match="om:OMA[om:OMS[@cd='limit1']]">
    <mml:apply>
      <mml:limit/>
      <mml:bvar>
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="4"/>
        </xsl:apply-templates>
      </mml:bvar>
      <xsl:choose>
        <xsl:when test="om:OMS[2]/@name='null'">
          <mml:lowlimit>
            <xsl:apply-templates select="*[2]"/>
          </mml:lowlimit>
        </xsl:when>
        <xsl:otherwise>
          <mml:condition>
            <mml:apply>
              <tendsto>
                <xsl:choose>
                  <xsl:when test="om:OMS[2]/@name='both_sides'">
                    <xsl:attribute name="type">all</xsl:attribute>
                  </xsl:when>
                  <xsl:when test="om:OMS[2]/@name='above'">
                    <xsl:attribute name="type">above</xsl:attribute>
                  </xsl:when>
                  <xsl:when test="om:OMS[2]/@name='below'">
                    <xsl:attribute name="type">below</xsl:attribute>
                  </xsl:when>
                </xsl:choose>
              </tendsto>
              <xsl:apply-templates select="." mode="getVar">
                <xsl:with-param name="NUM" select="4"/>
              </xsl:apply-templates>
              <xsl:apply-templates select="*[2]"/>
            </mml:apply>
          </mml:condition>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*[4]"/>
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: list1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: list, map, suchthat -->

  <!-- Trivial cases: none -->

  <!-- list -->
  <xsl:template match="om:OMA[om:OMS[@cd='list1' and @name='list']]">
    <mml:list>
      <xsl:apply-templates select="*[position()>1]"/>
    </mml:list>
  </xsl:template>

  <!-- map -->
  <xsl:template match="om:OMA[om:OMS[@cd='list1' and @name='map']]">
    <mml:list>
      <xsl:apply-templates select="." mode="map"/>
    </mml:list>
  </xsl:template>

  <!-- suchthat -->
  <xsl:template match="om:OMA[om:OMS[@cd='list1' and @name='suchthat']]">
    <mml:list>
      <xsl:apply-templates select="." mode="suchthat"/>
    </mml:list>
  </xsl:template>





  <!-- Content Dictionary: logic1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: and, equivalent, false, implies, not, or, true, xor -->

  <!-- Trivial cases: all -->
  <xsl:template match="om:OMS[@cd='logic1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>





  <!-- Content Dictionary: mathmltypes -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: complex_cartesian_type, complex_polar_type, constant_type, 
       fn_type, integer_type, list_type, matrix_type, rational_type, real_type, 
       set_type, type, vector_type -->

  <!-- (All mathmltypes elements) -->
  <xsl:template match="om:OMATTR[om:OMATP[om:OMS[@cd='mathmltypes' and @name='type']]]">
    <xsl:variable name="type_name" select="normalize-space(translate(substring-before(om:OMATP/om:OMS[2]/@name,'_type'),'_','-'))"/>
    <xsl:choose>
      <xsl:when test="*[2]=om:OMV">
        <mml:ci type="{$type_name}">
          <xsl:value-of select="normalize-space(*[2]/@name)"/>
        </mml:ci>
      </xsl:when>
      <xsl:when test="*[2]=om:OMI">
        <mml:cn type="{$type_name}">
          <xsl:variable name="x" select="normalize-space(*[2])"/>
          <xsl:choose>
            <xsl:when test="contains($x,'x')">
              <xsl:attribute name="base">16</xsl:attribute>
              <xsl:value-of select="concat(substring-before($x,'x'),substring-after($x,'x'))"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="$x"/> <!-- default is decimal -->
            </xsl:otherwise>
          </xsl:choose>
        </mml:cn>
      </xsl:when>
      <xsl:when test="*[2]=om:OMF[@dec]">
        <mml:cn type="{$type_name}">
          <xsl:value-of select="normalize-space(*[2]/@dec)"/>
        </mml:cn>
      </xsl:when>
      <xsl:when test="*[2]=om:OMF[@hex]">
        <mml:cn type="{$type_name}" base="16">
          <xsl:value-of select="normalize-space(*[2]/@hex)"/>
        </mml:cn>
      </xsl:when>
      <xsl:otherwise> <!-- MathML cannot add type attribute to other objects -->
        <xsl:comment>
          Content MathML cannot add type <xsl:value-of select="$type_name"/> for the object after this comment.
        </xsl:comment>
        <xsl:apply-templates select="*[2]"/>
      </xsl:otherwise>
    </xsl:choose> 
  </xsl:template>





  <!-- Content Dictionary: minmax1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: max, min -->
  <xsl:template match="om:OMS[@cd='minmax1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- Trivial cases: none -->

  <!-- max, min -->
  <xsl:template match="om:OMA[om:OMS[@cd='minmax1']]">
    <mml:apply>
      <xsl:element name="{om:OMS/@name}"/>
      <xsl:choose>
        <xsl:when test="*[2]=om:OMA[om:OMS[@cd='set1' and @name='set']]">
          <xsl:apply-templates select="om:OMA/*[position()>1]"/>
        </xsl:when>
        <xsl:when test="*[2]=om:OMA[om:OMS[@cd='multiset1' and @name='multiset']]">
          <xsl:apply-templates select="om:OMA/*[position()>1]"/>
        </xsl:when>
        <xsl:when test="*[2]=om:OMA[om:OMS[@cd='set1' and @name='suchthat']]">
          <xsl:apply-templates select="*[2]" mode="suchthat"/>
        </xsl:when>
        <xsl:when test="*[2]=om:OMA[om:OMS[@cd='set1' and @name='map']]">
          <xsl:apply-templates select="*[2]" mode="map"/>
        </xsl:when>
        <xsl:otherwise>
          <mml:bvar><mml:ci>x</mml:ci></mml:bvar>
          <mml:condition>
            <mml:apply>
              <in/>
              <mml:ci>x</mml:ci>
              <xsl:apply-templates select="*[2]"/>
            </mml:apply>
          </mml:condition>
        </xsl:otherwise>
      </xsl:choose>
    </mml:apply>
  </xsl:template>
  




  <!-- Content Dictionary: multiset1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: cartesian_product, emptyset, in, intersect, multiset, 
       notin, notprsubset, notsubset, prsubset, setdiff, size, subset, union -->

  <!-- Trivial cases: emptyset, in, interset, notin, notprsubset, notsubset, prsubset,
       subset, union -->
  <xsl:template match="om:OMS[@cd='multiset1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- cartesian_product -->
  <xsl:template match="om:OMS[@cd='multiset1' and @name='cartesian_product']">
    <mml:cartesianproduct/>
  </xsl:template>


  <!-- multiset -->
  <xsl:template match="om:OMA[om:OMS[@cd='multiset1' and @name='multiset']]">
    <mml:set type="multiset">
      <xsl:apply-templates select="*[position()>1]"/>
    </mml:set>
  </xsl:template>

  <!-- size -->
  <xsl:template match="om:OMS[@cd='multiset1' and @name='size']">
    <mml:card/>
  </xsl:template>

  

    

  <!-- Content Dictionary: nums1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains based_integer, e, gamma, i, infinity, NaN, pi, rational -->

  <!-- Trivial cases: pi, infinity -->
  <xsl:template match="om:OMS[@cd='nums1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- based_integer -->
  <!-- Note: Content MathML does not support base that is represented by a variable -->
  <xsl:template match="om:OMA[om:OMS[@cd='nums1' and @name='based_integer']]">
    <xsl:choose>
      <xsl:when test="*[2]=om:OMV">
        <mml:apply>
          <csymbol encoding="OpenMath" definitionURL="http://www.openmath.org/cd/nums1#based_integer"/>
            <xsl:apply-templates select="*[2]"/>
            <xsl:apply-templates select="*[3]"/>
        </mml:apply>
      </xsl:when>
      <xsl:otherwise>
        <mml:cn type="integer" base="{normalize-space(*[2])}">
          <xsl:value-of select="normalize-space(om:OMSTR)"/>
        </mml:cn>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- e -->
  <xsl:template match="om:OMS[@cd='nums1' and @name='e']">
    <mml:exponentiale/>
  </xsl:template>

  <!-- gamma -->
  <xsl:template match="om:OMS[@cd='nums1' and @name='gamma']">
    <mml:eulergamma/>
  </xsl:template>

  <!-- i -->
  <xsl:template match="om:OMS[@cd='nums1' and @name='i']">
    <mml:imaginaryi/>
  </xsl:template>

  <!-- NaN -->
  <xsl:template match="om:OMS[@cd='nums1' and @name='NaN']">
    <mml:notanumber/>
  </xsl:template>

  <!-- rational -->
  <!-- Note: Content MathML does not support rational numbers that are
       made up of variables or other mathematical objects -->
  <xsl:template match="om:OMA[om:OMS[@cd='nums1' and @name='rational']]">
    <xsl:choose>
      <xsl:when test="child::om:OMV or child::om:OMA">
        <mml:apply>
          <csymbol definitionURL="http://www.openmath.org/cd/nums1#rational"/>
          <xsl:apply-templates select="*[2]"/>
          <xsl:apply-templates select="*[3]"/>
        </mml:apply>
      </xsl:when>
      <xsl:otherwise>
	<mml:cn type="rational">
          <xsl:apply-templates select="*[2]" mode="convert"/>
	  <sep/>
          <xsl:apply-templates select="*[3]" mode="convert"/>
	</mml:cn>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  



  <!-- Content Dictionary: piece1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: otherwise, piece, piecewise -->

  <!-- Trivial cases: (All of the OMSs here are almost trivial, except
       that these functions are used without "apply" in Content MathML) -->

  <!-- piecewise, piece, otherwise -->
  <xsl:template match="om:OMA[om:OMS[@cd='piece1']]">
    <xsl:element name="{om:OMS/@name}">
      <xsl:apply-templates select="*[position()>1]"/>
    </xsl:element>
  </xsl:template>
  




  <!-- Content Dictionary: quant1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: forall, exists -->

  <!-- forall,exists -->
  <xsl:template match="om:OMBIND[om:OMS[@cd='quant1']]">
    <mml:apply>
      <xsl:element name="mml:{om:OMS[1]/@name}"/>
      <xsl:for-each select="om:OMBVAR/om:OMV">
        <mml:bvar>
          <xsl:apply-templates select="."/>
        </mml:bvar>
      </xsl:for-each>
      <xsl:apply-templates select="*[3]"/>
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: relation1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: approx, eq, geq, gt, leq, lt, neq -->

  <!-- Trivial cases: all -->
  <xsl:template match="om:OMS[@cd='relation1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>





  <!-- Content Dictionary: setname1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: C, N, P, Q, R, Z -->

  <!-- Trivial cases: none -->

  <!-- P -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='P']">
    <mml:primes/>
  </xsl:template>

  <!-- N -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='N']">
    <mml:naturalnumbers/>
  </xsl:template>

  <!-- Z -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='Z']">
    <mml:integers/>
  </xsl:template>

  <!-- Z -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='Q']">
    <mml:rationals/>
  </xsl:template>
  
  <!-- R -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='R']">
    <mml:reals/>
  </xsl:template>

  <!-- C -->
  <xsl:template match="om:OMS[@cd='setname1' and @name='C']">
    <mml:complexes/>
  </xsl:template>





  <!-- Content Dictionary: rounding1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: ceiling, floor, round, trunc -->

  <!-- Trivial Cases: ceiling, floor -->
  <xsl:template match="om:OMS[@cd='rounding1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>
  
  <!-- trunc -->
  <xsl:template match="om:OMA[om:OMS[@cd='rounding1' and @name='trunc']]">
    <mml:apply>
      <mml:quotient/>
      <xsl:apply-templates select="*[2]"/>
      <mml:cn>1</mml:cn>
    </mml:apply>
  </xsl:template>

  <!-- round -->
  <xsl:template match="om:OMA[om:OMS[@cd='rounding1' and @name='round']]">
    <mml:piecewise>
      <mml:piece>
        <mml:apply>
          <mml:floor/>
          <mml:apply>
            <mml:plus/>
            <mml:cn>0.5</mml:cn>
            <xsl:apply-templates select="*[2]"/>
          </mml:apply>
        </mml:apply>
        <mml:apply>
          <mml:geq/>
          <xsl:apply-templates select="*[2]"/>
          <mml:cn>0</mml:cn>
        </mml:apply>
      </mml:piece>
      <mml:piece>
        <mml:apply>
          <mml:ceiling/>
          <mml:apply>
            <mml:minus/>
            <xsl:apply-templates select="*[2]"/>
            <mml:cn>0.5</mml:cn>
          </mml:apply>
        </mml:apply>
        <mml:apply>
          <mml:lt/>
          <xsl:apply-templates select="*[2]"/>
          <mml:cn>0</mml:cn>
        </mml:apply>
      </mml:piece>
    </mml:piecewise>
  </xsl:template>





  <!-- Content Dictionary: set1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: cartesian_product, emptyset, in, intersect, map, notin, 
       notprsubset, notsubset, prsubset, set, setdiff, size, subset, suchthat, union -->

  <!-- Trivial cases: emptyset, in, intersect, notin, notprsubset, notsubset, prsubset
       setdiff, subset, union -->
       
  <xsl:template match="om:OMS[@cd='set1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- cartesian_product--> 
  <xsl:template match="om:OMS[@cd='set1' and @name='cartesian_product']">
    <mml:cartesianproduct/>
  </xsl:template>

  <!-- map -->
  <xsl:template match="om:OMA[om:OMS[@cd='set1' and @name='map']]">
    <mml:set>
      <xsl:apply-templates select="." mode="map"/>
    </mml:set>
  </xsl:template>

  <!-- size -->
  <xsl:template match="om:OMS[@cd='set1' and @name='size']">
    <mml:card/>
  </xsl:template>

  <!-- suchthat -->
  <xsl:template match="om:OMA[om:OMS[@cd='set1' and @name='suchthat']]">
    <mml:set>
      <xsl:apply-templates select="." mode="suchthat"/>
    </mml:set>
  </xsl:template>





  <!-- Content Dictionary: s_data1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: mean, median, mode, moment, sdev, variance -->


  <!-- Trivial cases: mean, median, mode, sdev, variance -->
  <xsl:template match="om:OMS[@cd='s_data1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- moment -->
  <xsl:template match="om:OMA[om:OMS[@cd='s_data1' and @name='moment']]">
    <mml:apply>
      <mml:moment/>
      <mml:degree>
        <xsl:apply-templates select="*[2]"/>
      </mml:degree>
      <momentabout>
        <xsl:apply-templates select="*[3]"/>
      </momentabout>
      <xsl:apply-templates select="*[position()>3]"/> 
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: s_dist1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: mean, moment, sdev, variance  -->

  <!-- Trivial cases: mean, sdev, variance -->
  <xsl:template match="om:OMS[@cd='s_dist1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- moment -->
  <xsl:template match="om:OMA[om:OMS[@cd='s_dist1' and @name='moment']]">
    <mml:apply>
      <mml:moment/>
      <mml:degree>
        <xsl:apply-templates select="*[2]"/>
      </mml:degree>
      <mml:momentabout>
        <xsl:apply-templates select="*[3]"/>
      </mml:momentabout>
      <xsl:apply-templates select="*[position()>3]"/> 
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: transc1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: arccos, arccosh, arccot, arccoth, arccsc, 
       arccsch, arcsec, arcsech, arcsin, arcsinh, arctan, arctanh, cos, 
       cosh, cot, coth, csc, csch, exp, ln, log, sec, sech, sin, sinh, 
       tan, tanh -->

  <!-- Trivial cases: all except log -->
  <xsl:template match="om:OMS[@cd='transc1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>
  


  <!-- log -->
  <xsl:template match="om:OMA[om:OMS[@cd='transc1' and @name='log']]">
    <mml:apply>
      <mml:log/>
      <mml:logbase>
        <xsl:apply-templates select="*[2]"/>
      </mml:logbase>
      <xsl:apply-templates select="*[3]"/>
    </mml:apply>
  </xsl:template>





  <!-- Content Dictionary: veccalc1 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: curl, divergence, grad, Laplacian -->

  <!-- Trivial cases: all except Laplacian -->
  <xsl:template match="om:OMS[@cd='veccalc1']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>

  <!-- Laplacian -->
  <!-- Note: Capital "L" -->
  <xsl:template match="om:OMS[@cd='veccalc1' and @name='Laplacian']">
    <mml:laplacian/>
  </xsl:template>





  <!-- Content Dictionary: altenc -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: LaTeX_encoding, MathML_encoding -->

  <!-- Trivial cases: none -->

  <!-- (Everything in altenc) -->
  <xsl:template match="om:OMATTR[om:OMATP[om:OMS[@cd='altenc']]]">
    <mml:semantics>
      <xsl:apply-templates select="*[2]"/>
      <xsl:apply-templates select="om:OMATP/child::om:OMS"/>
    </mml:semantics>
  </xsl:template>

  <!-- MathML_encoding -->
  <xsl:template match="om:OMS[@cd='altenc' and @name='MathML_encoding']">
    <annotation-xml encoding="MathML">
      <xsl:value-of select="normalize-space(following-sibling::*[position()=1])"/> <!-- OMXML or OMSTR-->
    </annotation-xml>
  </xsl:template>

  <!-- LaTeX_encoding -->
  <xsl:template match="om:OMS[@cd='altenc' and @name='LaTeX_encoding']">
    <annotation encoding="LaTeX">
      <xsl:value-of select="normalize-space(following::om:OMSTR)"/>
    </annotation>
  </xsl:template>







  
  <!-- **************************************************** -->
  <!-- ************** Not in MathML group CDs ************* -->
  <!-- **************************************************** -->

  <!-- Everything below should not be handled by the Trivial case!) -->

  <!-- Content Dictionary: linalg3 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- (column) vector -->
  <xsl:template match="om:OMA[om:OMS[@cd='linalg3' and @name='vector']]">
    <mml:vector>
      <xsl:apply-templates select="*[position()>1]"/>
    </mml:vector>
  </xsl:template>





  <!-- Content Dictionary: arith2 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: inverse, times  -->

  <!-- times -->
  <!-- Note: This function is n-ary just like MathML! -->
  <xsl:template match="om:OMS[@cd='arith2' and @name='times']">
    <mml:times/>
  </xsl:template>





  <!-- Content Dictionary: error -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- (all errors) -->
  <xsl:template match="om:OME">
    <mml:mtext>
      <xsl:text>ERROR:</xsl:text>
      <xsl:text>  Error Type: </xsl:text><xsl:value-of select="om:OMS[1]/@name"/>
      <xsl:text>  Error occured in CD: </xsl:text><xsl:value-of select="om:OMS[2]/@cd"/>
      <xsl:text>  Error occured in symbol: </xsl:text><xsl:value-of select="om:OMS[2]/@name"/>
    </mml:mtext>
  </xsl:template>





  <!-- Content Dictionary: moreerrors -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->

  <!-- This CD contains: algorithm, asynchronousError, encodingError, limitation, 
       unexpected -->
  
  <!-- (all) -->
  <xsl:template match="om:OMA[om:OMS[@cd='moreerrors']]">
    <mml:mtext>
      <xsl:text>ERROR:</xsl:text>
      <xsl:text>  Error Type: </xsl:text><xsl:value-of select="normalize-space(om:OMS/@name)"/>
      <xsl:text>  Description: </xsl:text><xsl:value-of select="normalize-space(om:OMSTR)"/>
    </mml:mtext>
  </xsl:template>





  <!-- Content Dicitionary: transc3 -->
  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->  

  <!-- This CD contains: arccos, arccosh, arccot, arccoth, arccsc, arccsch, 
	arcsec, arcsech, arcsin, arcsinh, arctan, arctanh, ln, log --> 
  
  <!-- (all except log) -->
  <xsl:template match="om:OMS[@cd='transc3']">
    <xsl:element name="mml:{@name}"/>
  </xsl:template>
  
  <!-- log -->
  <xsl:template match="om:OMA[om:OMS[@cd='transc3' and @name='log']]">
    <mml:apply>
      <mml:log/>      
      <mml:logbase>
        <xsl:apply-templates select="*[3]"/>
      </mml:logbase>
      <xsl:apply-templates select="*[2]"/>
    </mml:apply>
  </xsl:template>







  <!-- **************************************************** -->
  <!-- **************** EVERYTHING ELSE ******************* -->
  <!-- **************************************************** -->

  <!-- Note: Rather than hard code all of the CDs, I just assign the lowest
       priority among all templates. -->
  <xsl:template match="om:OMS[@cd and @name]" priority="-10">
    <mml:csymbol>
      <xsl:attribute name="definitionURL">
        <xsl:value-of select="concat(concat(concat('http://www.openmath.org/cd/',@cd),'#'),@name)"/>
      </xsl:attribute>
    </mml:csymbol>
  </xsl:template>










  <!-- **************************************************** -->
  <!-- **************** HELPER TEMPLATES ****************** -->
  <!-- **************************************************** -->

  <!-- All mode "convert" templates are for converting OMSs or OMIs to
       numbers including in various cn containing <sep/> -->
  
  <xsl:template match="om:OMS[@cd='alg1' and @name='zero']" mode="convert">0</xsl:template>

  <xsl:template match="om:OMS[@cd='alg1' and @name='one']" mode="convert">1</xsl:template>

  <xsl:template match="om:OMS" mode="convert">
    <xsl:choose>
      <xsl:when test="@name='pi'">&#x003C0;</xsl:when>
      <xsl:when test="@name='i'">&#x02148;</xsl:when>
      <xsl:when test="@name='NaN'">&#xF0001;</xsl:when>
      <xsl:when test="@name='gamma'">&#x003B3;</xsl:when>
      <xsl:when test="@name='e'">&#x02147E;</xsl:when>
      <xsl:when test="@name='true'">&#xF0002;</xsl:when>
      <xsl:when test="@name='false'">&#xF0003;</xsl:when>
      <xsl:when test="@name='infinity'">&#x0221E;</xsl:when>
      <xsl:otherwise><xsl:value-of select="normalize-space(.)"/></xsl:otherwise> <!-- for debugging -->
    </xsl:choose>
  </xsl:template>
  
  <xsl:template match="om:OMI" mode="convert">
    <xsl:variable name="x" select="normalize-space(.)"/>
    <xsl:choose>
      <xsl:when test="contains($x,'x')">
        <xsl:value-of select="concat(substring-before($x,'x'),substring-after($x,'x'))"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$x"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="om:OMF" mode="convert">
    <xsl:value-of select="@*"/>
  </xsl:template>


  <!-- The following templates, with mode "map" and "suchthat", are used to contruct sets or lists
       without enumerating every element -->

  <xsl:template match="om:OMA" mode="map">
    <mml:bvar>
      <xsl:apply-templates select="." mode="getVar">
        <xsl:with-param name="NUM" select="2"/>
      </xsl:apply-templates>
    </mml:bvar>
    <mml:condition>
      <mml:apply>
        <mml:in/>
        <xsl:apply-templates select="." mode="getVar">
          <xsl:with-param name="NUM" select="2"/>
        </xsl:apply-templates>
        <xsl:apply-templates select="*[3]"/>
      </mml:apply>
    </mml:condition>
    <xsl:apply-templates select="*[2]"/>
  </xsl:template>

  <xsl:template match="om:OMA" mode="suchthat">
    <mml:bvar>
      <xsl:apply-templates select="." mode="getVar">
        <xsl:with-param name="NUM" select="3"/>
      </xsl:apply-templates>
    </mml:bvar>
    <mml:condition>
      <mml:apply>
        <mml:and/>
        <xsl:apply-templates select="*[3]"/>
        <mml:apply>
          <in/>
          <xsl:apply-templates select="." mode="getVar">
            <xsl:with-param name="NUM" select="3"/>            
          </xsl:apply-templates>
          <xsl:apply-templates select="*[2]"/>
        </mml:apply>
      </mml:apply>
    </mml:condition>
  </xsl:template>


  <!-- This template is for getting bound variables (all variables in <OMBIND>) -->
  <!-- Note: Default bound variable is "x" -->
  <xsl:template match="om:OMA" mode="getVar">
    <xsl:param name="NUM" select="3"/>
    <xsl:choose>
      <xsl:when test="*[$NUM]=om:OMBIND">
        <xsl:apply-templates select="*[$NUM]/om:OMBVAR/om:OMV[position()>0]"/>
      </xsl:when>
     <!-- <xsl:otherwise>  default 
        <mml:ci>
        	<xsl:apply-templates/>
        	</mml:ci>
      </xsl:otherwise>-->
    </xsl:choose>
  </xsl:template>


</xsl:stylesheet>
