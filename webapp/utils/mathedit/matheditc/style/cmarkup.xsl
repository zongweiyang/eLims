<?xml version='1.0' encoding="UTF-8"?>
<xsl:stylesheet
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:mml="http://www.w3.org/1998/Math/MathML"
		version='1.0'>
                
<!-- ====================================================================== -->
<!-- $Id: cmarkup.xsl,v 1.8 2003/06/10 12:24:04 shade33 Exp $
     This file is part of the XSLT MathML Library distribution.
     See ./README or http://www.raleigh.ru/MathML/mmltex for
     copyright and other information                                        -->
<!-- ====================================================================== -->

<!-- 4.4.1.1 cn -->
<xsl:template match="mml:cn"><xsl:apply-templates/></xsl:template>

<xsl:template match="mml:cn[@type='complex-cartesian']">
	<xsl:apply-templates select="text()[1]"/>
  	<xsl:text>+</xsl:text>
	<xsl:apply-templates select="text()[2]"/>
	<xsl:text>i</xsl:text>
</xsl:template>

<xsl:template match="mml:cn[@type='rational']">
	<xsl:apply-templates select="text()[1]"/>
	<xsl:text>/</xsl:text>
	<xsl:apply-templates select="text()[2]"/>
</xsl:template>

<xsl:template match="mml:cn[@type='integer' and @base!=10]">
		<xsl:apply-templates/>
		<xsl:text>_{</xsl:text><xsl:value-of select="@base"/><xsl:text>}</xsl:text>
</xsl:template>

<xsl:template match="mml:cn[@type='complex-polar']">
	<xsl:apply-templates select="text()[1]"/>
	<xsl:text>e^{i </xsl:text>
	<xsl:apply-templates select="text()[2]"/>
	<xsl:text>}</xsl:text>
</xsl:template>

<xsl:template match="mml:cn[@type='e-notation']">
    <xsl:apply-templates select="text()[1]"/>
    <xsl:text>E</xsl:text>
    <xsl:apply-templates select="text()[2]"/>
</xsl:template>

<!-- 4.4.1.1 ci 4.4.1.2 csymbol -->
<xsl:template match="mml:ci | mml:csymbol">
	<xsl:choose>
		<xsl:when test="string-length(normalize-space(text()))>1">
			<xsl:text>\mathrm{</xsl:text><xsl:apply-templates/><xsl:text>}</xsl:text>
		</xsl:when>
		<xsl:otherwise><xsl:apply-templates/></xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- 4.4.2.1 apply 4.4.2.2 reln -->
<xsl:template match="mml:apply | mml:reln">
	<xsl:apply-templates select="*[1]">
	<!-- <? -->
		<xsl:with-param name="p" select="10"/>
	</xsl:apply-templates>
	<!-- ?> -->
 	<xsl:text>(</xsl:text>
	<xsl:for-each select="*[position()>1]">
		<xsl:apply-templates select="."/>
		<xsl:if test="not(position()=last())"><xsl:text>, </xsl:text></xsl:if>
	</xsl:for-each>
 	<xsl:text>)</xsl:text>
</xsl:template>

<!-- 4.4.2.3 fn -->
<xsl:template match="mml:fn[mml:apply[1]]"> <!-- for mml:fn using default rule -->
	<xsl:text>(</xsl:text><xsl:apply-templates/><xsl:text>)</xsl:text>
</xsl:template>

<!-- 4.4.2.4 interval -->
<xsl:template match="mml:interval[*[2]]">
	<xsl:choose>
		<xsl:when test="@closure='open' or @closure='open-closed'">
			<xsl:text>\left(</xsl:text>		
		</xsl:when>
		<xsl:otherwise><xsl:text>\left[</xsl:text></xsl:otherwise> 
	</xsl:choose>
	<xsl:apply-templates select="*[1]"/>
	<xsl:text> , </xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:choose>
		<xsl:when test="@closure='open' or @closure='closed-open'">
			<xsl:text>\right)</xsl:text>		
		</xsl:when>
		<xsl:otherwise><xsl:text>\right]</xsl:text></xsl:otherwise> 
	</xsl:choose>
</xsl:template>

<xsl:template match="mml:interval">
	<xsl:text>\left\{</xsl:text><xsl:apply-templates/><xsl:text>\right\}</xsl:text>
</xsl:template>

<!-- 4.4.2.5 inverse -->
<xsl:template match="mml:apply[*[1][self::mml:inverse]]">
	<xsl:apply-templates select="*[2]"/><xsl:text>^{(-1)}</xsl:text>
</xsl:template>

<!-- 4.4.2.6 sep 4.4.2.7 condition -->
<xsl:template match="mml:sep | mml:condition"><xsl:apply-templates/></xsl:template>

<!-- 4.4.2.9 lambda -->
<xsl:template match="mml:lambda">
	<xsl:apply-templates select="mml:bvar/*"/>
  <xsl:text>\mapsto </xsl:text>
  <xsl:apply-templates select="*[last()]"/>
<!--	Other variant 
	<xsl:text>\mathrm{lambda}\: </xsl:text>
  	<xsl:apply-templates select="mml:bvar/*"/>
  	<xsl:text>.\: </xsl:text>
  <xsl:apply-templates select="*[last()]"/> -->
</xsl:template>

<!-- 4.4.2.10 compose -->
<xsl:template match="mml:apply[*[1][self::mml:compose]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\circ </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.2.11 ident -->
<xsl:template match="mml:ident"><xsl:text>\mathrm{id}</xsl:text></xsl:template>

<!-- 4.4.2.12 domain 4.4.2.13 codomain 4.4.2.14 image 4.4.3.21 arg 4.4.3.24 lcm
		4.4.5.9 grad 4.4.5.10 curl 4.4.9.4 median 4.4.9.5 mode-->
<xsl:template match="mml:domain | mml:codomain | mml:image | mml:arg | mml:lcm | mml:grad |
								 mml:curl | mml:median | mml:mode">
	<xsl:text>\mathop{\mathrm{</xsl:text>
	<xsl:value-of select="local-name()"/>
	<xsl:text>}}</xsl:text>
</xsl:template>

<!-- 4.4.2.15 domainofapplication -->
<xsl:template match="mml:domainofapplication"/>

<!-- 4.4.2.16 piecewise -->
<xsl:template match="mml:piecewise">
	<xsl:text>\begin{cases}</xsl:text>
	<xsl:apply-templates select="mml:piece"/>
	<xsl:apply-templates select="mml:otherwise"/>
	<xsl:text>\end{cases}</xsl:text>
</xsl:template>

<xsl:template match="mml:piece">
		<xsl:apply-templates select="*[1]"/>
		<xsl:text> &amp; \text{if $</xsl:text>
		<xsl:apply-templates select="*[2]"/>
		<xsl:text>$}</xsl:text>
		<xsl:if test="not(position()=last()) or ../mml:otherwise"><xsl:text>\\ </xsl:text></xsl:if>
</xsl:template>

<xsl:template match="mml:otherwise">
	<xsl:apply-templates select="*[1]"/>
	<xsl:text> &amp; \text{otherwise}</xsl:text>
</xsl:template>

<!-- 4.4.3.1 quotient -->
<xsl:template match="mml:apply[*[1][self::mml:quotient]]">
	<xsl:text>\left\lfloor\frac{</xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>}{</xsl:text>
	<xsl:apply-templates select="*[3]"/>
	<xsl:text>}\right\rfloor </xsl:text>
</xsl:template>

<!-- 4.4.3.2 factorial -->
<xsl:template match="mml:apply[*[1][self::mml:factorial]]">
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
	<xsl:text>!</xsl:text>
</xsl:template>

<!-- 4.4.3.3 divide -->
<xsl:template match="mml:apply[*[1][self::mml:divide]]">
	<xsl:param name="p" select="0"/>
  <xsl:param name="this-p" select="3"/>
  <xsl:if test="$this-p &lt; $p"><xsl:text>\left(</xsl:text></xsl:if>
  <xsl:text>\frac{</xsl:text>
	<xsl:apply-templates select="*[2]"/>
<!--		<xsl:with-param name="p" select="$this-p"/>
	</xsl:apply-templates>-->
	<xsl:text>}{</xsl:text>
	<xsl:apply-templates select="*[3]"/>
<!--    	<xsl:with-param name="p" select="$this-p"/>
	</xsl:apply-templates>-->
	<xsl:text>}</xsl:text>
	<xsl:if test="$this-p &lt; $p"><xsl:text>\right)</xsl:text></xsl:if>
</xsl:template>

<!-- 4.4.3.4 max min -->
<xsl:template match="mml:apply[*[1][self::mml:max or self::mml:min]]">
	<xsl:text>\</xsl:text>
	<xsl:value-of select="local-name(*[1])"/>
	<xsl:text>\{</xsl:text>
   <xsl:choose>
		<xsl:when test="mml:condition">
   		<xsl:apply-templates select="*[last()]"/>
   		<xsl:text>\mid </xsl:text>
			<xsl:apply-templates select="mml:condition/node()"/>
		</xsl:when>
		<xsl:otherwise>
			<xsl:for-each select="*[position() &gt; 1]">
				<xsl:apply-templates select="."/>
				<xsl:if test="position() !=last()"><xsl:text> , </xsl:text></xsl:if>
			</xsl:for-each>
		</xsl:otherwise>
   </xsl:choose>
	<xsl:text>\}</xsl:text>
</xsl:template>

<!-- 4.4.3.5  minus-->
<xsl:template match="mml:apply[*[1][self::mml:minus] and count(*)=2]">
	<xsl:text>-</xsl:text>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="5"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="mml:apply[*[1][self::mml:minus] and count(*)&gt;2]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo">-</xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="2"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.6  plus-->
<xsl:template match="mml:apply[*[1][self::mml:plus]]">
  <xsl:param name="p" select="0"/>
  <xsl:if test="$p &gt; 2">
		<xsl:text>(</xsl:text>
	</xsl:if>
  <xsl:for-each select="*[position()&gt;1]">
   <xsl:if test="position() &gt; 1">
    <xsl:choose>
      <xsl:when test="self::mml:apply[*[1][self::mml:times] and
      *[2][self::mml:apply/*[1][self::mml:minus] or self::mml:cn[not(mml:sep) and
      (number(.) &lt; 0)]]]">-</xsl:when>
      <xsl:otherwise>+</xsl:otherwise>
    </xsl:choose>
   </xsl:if>   
    <xsl:choose>
      <xsl:when test="self::mml:apply[*[1][self::mml:times] and
      *[2][self::mml:cn[not(mml:sep) and (number(.) &lt;0)]]]">
			<xsl:value-of select="-(*[2])"/>
			<xsl:apply-templates select=".">
		     <xsl:with-param name="first" select="2"/>
		     <xsl:with-param name="p" select="2"/>
		   </xsl:apply-templates>
       </xsl:when>
      <xsl:when test="self::mml:apply[*[1][self::mml:times] and
      *[2][self::mml:apply/*[1][self::mml:minus]]]">
				<xsl:apply-templates select="./*[2]/*[2]"/>
				<xsl:apply-templates select=".">
					<xsl:with-param name="first" select="2"/>
					<xsl:with-param name="p" select="2"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select=".">
					<xsl:with-param name="p" select="2"/>
				</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:for-each>
	<xsl:if test="$p &gt; 2">
		<xsl:text>)</xsl:text>
	</xsl:if>
</xsl:template>

<!-- 4.4.3.7 power -->
<xsl:template match="mml:apply[*[1][self::mml:power]]">
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="5"/>
	</xsl:apply-templates>
	<xsl:text>^{</xsl:text>
	<xsl:apply-templates select="*[3]">
		<xsl:with-param name="p" select="5"/>
	</xsl:apply-templates>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.3.8 remainder -->
<xsl:template match="mml:apply[*[1][self::mml:rem]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo">\mod </xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="3"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.9  times-->
<xsl:template match="mml:apply[*[1][self::mml:times]]" name="times">
  <xsl:param name="p" select="0"/>
  <xsl:param name="first" select="1"/>
  <xsl:if test="$p &gt; 3"><xsl:text>(</xsl:text></xsl:if>
  <xsl:for-each select="*[position()&gt;1]">
		<xsl:if test="position() &gt; 1">
			<xsl:choose>
				<xsl:when test="self::mml:cn">\times <!-- times --></xsl:when>
				<xsl:otherwise><!--invisible times--></xsl:otherwise>
			</xsl:choose>
		</xsl:if> 
		<xsl:if test="position()&gt;= $first">
			<xsl:apply-templates select=".">
				<xsl:with-param name="p" select="3"/>
			</xsl:apply-templates>
		</xsl:if>
	</xsl:for-each>
  <xsl:if test="$p &gt; 3"><xsl:text>)</xsl:text></xsl:if>
</xsl:template>

<!-- 4.4.3.10 root -->
<xsl:template match="mml:apply[*[1][self::mml:root]]">
	<xsl:text>\sqrt</xsl:text>
	<xsl:if test="mml:degree!=2">
		<xsl:text>[</xsl:text>
		<xsl:apply-templates select="mml:degree/*"/>
		<xsl:text>]</xsl:text>
	</xsl:if>
	<xsl:text>{</xsl:text>
	<xsl:apply-templates select="*[position()&gt;1 and not(self::mml:degree)]"/>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.3.11 gcd -->
<xsl:template match="mml:gcd"><xsl:text>\gcd </xsl:text></xsl:template>

<!-- 4.4.3.12 and -->
<xsl:template match="mml:apply[*[1][self::mml:and]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\land <!-- and --></xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.13 or -->
<xsl:template match="mml:apply[*[1][self::mml:or]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="3"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\lor </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.14 xor -->
<xsl:template match="mml:apply[*[1][self::mml:xor]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="3"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\mathop{\mathrm{xor}}</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.15 not -->
<xsl:template match="mml:apply[*[1][self::mml:not]]">
	<xsl:text>\neg </xsl:text>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<!-- 4.4.3.16 implies -->
<xsl:template match="mml:apply[*[1][self::mml:implies]] | mml:reln[*[1][self::mml:implies]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo">\implies </xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="3"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.3.17 forall 4.4.3.18 exists -->
<xsl:template match="mml:apply[*[1][self::mml:forall or self::mml:exists]]">
	<xsl:text>\</xsl:text>
	<xsl:value-of select="local-name(*[1])"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="mml:bvar"/>
	<xsl:if test="mml:condition">
		<xsl:text>, </xsl:text><xsl:apply-templates select="mml:condition"/>
	</xsl:if>
	<xsl:if test="*[last()][local-name()!='condition'][local-name()!='bvar']">
		<xsl:text>\colon </xsl:text>
	  <xsl:apply-templates select="*[last()]"/>
  </xsl:if>
</xsl:template>

<!-- 4.4.3.19 abs -->
<xsl:template match="mml:apply[*[1][self::mml:abs]]">
	<xsl:text>\left|</xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>\right|</xsl:text>
</xsl:template>

<!-- 4.4.3.20 conjugate -->
<xsl:template match="mml:apply[*[1][self::mml:conjugate]]">
	<xsl:text>\overline{</xsl:text><xsl:apply-templates select="*[2]"/><xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.3.22 real -->
<xsl:template match="mml:real"><xsl:text>\Re </xsl:text></xsl:template>

<!-- 4.4.3.23 imaginary -->
<xsl:template match="mml:imaginary"><xsl:text>\Im </xsl:text></xsl:template>

<!-- 4.4.3.25 floor -->
<xsl:template match="mml:apply[*[1][self::mml:floor]]">
	<xsl:text>\lfloor </xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>\rfloor </xsl:text>
</xsl:template>

<!-- 4.4.3.25 ceiling -->
<xsl:template match="mml:apply[*[1][self::mml:ceiling]]">
	<xsl:text>\lceil </xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>\rceil </xsl:text>
</xsl:template>

<!-- 4.4.4.1 eq -->
<xsl:template match="mml:apply[*[1][self::mml:eq]] | mml:reln[*[1][self::mml:eq]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">=</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.2 neq -->
<xsl:template match="mml:apply[*[1][self::mml:neq]] | mml:reln[*[1][self::mml:neq]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\neq </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.3 gt -->
<xsl:template match="mml:apply[*[1][self::mml:gt]] | mml:reln[*[1][self::mml:gt]]">
<xsl:param name="p" select="0"/>
<xsl:call-template name="infix">
	<xsl:with-param name="this-p" select="1"/>
	<xsl:with-param name="p" select="$p"/>
	<xsl:with-param name="mo">&gt; </xsl:with-param>
</xsl:call-template>
</xsl:template>

<!-- 4.4.4.4 lt -->
<xsl:template match="mml:apply[*[1][self::mml:lt]] | mml:reln[*[1][self::mml:lt]]">
<xsl:param name="p" select="0"/>
<xsl:call-template name="infix">
	<xsl:with-param name="this-p" select="1"/>
	<xsl:with-param name="p" select="$p"/>
	<xsl:with-param name="mo">&lt; </xsl:with-param>
</xsl:call-template>
</xsl:template>

<!-- 4.4.4.5 geq -->
<xsl:template match="mml:apply[*[1][self::mml:geq]] | mml:reln[*[1][self::mml:geq]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\ge </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.6 leq -->
<xsl:template match="mml:apply[*[1][self::mml:leq]] | mml:reln[*[1][self::mml:leq]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\le </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.7 equivalent -->
<xsl:template match="mml:apply[*[1][self::mml:equivalent]] | mml:reln[*[1][self::mml:equivalent]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\equiv </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.8 approx -->
<xsl:template match="mml:apply[*[1][self::mml:approx]] | mml:reln[*[1][self::mml:approx]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="1"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\approx </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.4.9 factorof -->
<xsl:template match="mml:apply[*[1][self::mml:factorof]] | mml:reln[*[1][self::mml:factorof]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo"> | </xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="3"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.5.1 int -->
<xsl:template match="mml:apply[*[1][self::mml:int]]">
	<xsl:text>\int</xsl:text>
	<xsl:if test="mml:lowlimit/*|mml:interval/*[1]|mml:condition/*">
		<xsl:text>_{</xsl:text>
		<xsl:apply-templates select="mml:lowlimit/*|mml:interval/*[1]|mml:condition/*"/>
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:if test="mml:uplimit/*|mml:interval/*[2]">
		<xsl:text>^{</xsl:text>
		<xsl:apply-templates select="mml:uplimit/*|mml:interval/*[2]"/>
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="*[last()]"/>
	<xsl:text>\,d </xsl:text>
	<xsl:apply-templates select="mml:bvar"/>
</xsl:template>

<!-- 4.4.5.2 diff -->
<xsl:template match="mml:apply[*[1][self::mml:diff] and mml:ci and count(*)=2]" priority="2">
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>^\prime </xsl:text>
</xsl:template>

<xsl:template match="mml:apply[*[1][self::mml:diff]]" priority="1">
	<xsl:text>\frac{</xsl:text>
	<xsl:choose>
		<xsl:when test="mml:bvar/mml:degree">
			<xsl:text>d^{</xsl:text>
			<xsl:apply-templates select="mml:bvar/mml:degree/node()"/>
			<xsl:text>}</xsl:text>
			<xsl:apply-templates select="*[last()]"/>
			<xsl:text>}{d</xsl:text>
			<xsl:apply-templates select="mml:bvar/node()"/>
			<xsl:text>^{</xsl:text>
			<xsl:apply-templates select="mml:bvar/mml:degree/node()"/>
			<xsl:text>}</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:text>d </xsl:text>
			<xsl:apply-templates select="*[last()]"/>
			<xsl:text>}{d </xsl:text>
			<xsl:apply-templates select="mml:bvar"/>
		</xsl:otherwise>
	</xsl:choose>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.5.3 partialdiff -->
<xsl:template match="mml:apply[*[1][self::mml:partialdiff] and mml:list and mml:ci and count(*)=3]" priority="2">
	<xsl:text>D_{</xsl:text>
	<xsl:for-each select="mml:list[1]/*">
		<xsl:apply-templates select="."/>
		<xsl:if test="position()&lt;last()"><xsl:text>, </xsl:text></xsl:if>
	</xsl:for-each>
	<xsl:text>}</xsl:text>
	<xsl:apply-templates select="*[3]"/>
</xsl:template>

<xsl:template match="mml:apply[*[1][self::mml:partialdiff]]" priority="1">
	<xsl:text>\frac{\partial^{</xsl:text>
	<xsl:choose>
		<xsl:when test="mml:degree">
			<xsl:apply-templates select="mml:degree/node()"/>
		</xsl:when>
		<xsl:when test="mml:bvar/mml:degree[string(number(.))='NaN']">
			<xsl:for-each select="mml:bvar/mml:degree">
				<xsl:apply-templates select="node()"/>
				<xsl:if test="position()&lt;last()"><xsl:text>+</xsl:text></xsl:if>
			</xsl:for-each>
			<xsl:if test="count(mml:bvar[not(mml:degree)])&gt;0">
				<xsl:text>+</xsl:text>
				<xsl:value-of select="count(mml:bvar[not(mml:degree)])"/>
			</xsl:if>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="sum(mml:bvar/mml:degree)+count(mml:bvar[not(mml:degree)])"/>
		</xsl:otherwise>
	</xsl:choose>
	<xsl:text>}</xsl:text>
	<xsl:apply-templates select="*[last()]"/>
	<xsl:text>}{</xsl:text>
	<xsl:for-each select="mml:bvar">
		<xsl:text>\partial </xsl:text>
		<xsl:apply-templates select="node()"/>
		<xsl:if test="mml:degree">
			<xsl:text>^{</xsl:text>
			<xsl:apply-templates select="mml:degree/node()"/>
			<xsl:text>}</xsl:text>
		</xsl:if>
	</xsl:for-each>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.2.8 declare 4.4.5.4 lowlimit 4.4.5.5 uplimit 4.4.5.7 degree 4.4.9.5 momentabout -->
<xsl:template match="mml:declare | mml:lowlimit | mml:uplimit | mml:degree | mml:momentabout"/>

<!-- 4.4.5.6  bvar-->
<xsl:template match="mml:bvar">
	<xsl:apply-templates/>
	<xsl:if test="following-sibling::mml:bvar"><xsl:text>, </xsl:text></xsl:if>
</xsl:template>

<!-- 4.4.5.8 divergence-->
<xsl:template match="mml:divergence"><xsl:text>\mathop{\mathrm{div}}</xsl:text></xsl:template>

<!-- 4.4.5.11 laplacian-->
<xsl:template match="mml:laplacian"><xsl:text>\nabla^2 </xsl:text></xsl:template>

<!-- 4.4.6.1 set -->
<xsl:template match="mml:set">
	<xsl:text>\{</xsl:text><xsl:call-template name="set"/><xsl:text>\}</xsl:text>
</xsl:template>

<!-- 4.4.6.2 list -->
<xsl:template match="mml:list">
	<xsl:text>\left[</xsl:text><xsl:call-template name="set"/><xsl:text>\right]</xsl:text>
</xsl:template>

<xsl:template name="set">
   <xsl:choose>
		<xsl:when test="mml:condition">
   		<xsl:apply-templates select="mml:bvar/*[not(self::bvar or self::condition)]"/>
   		<xsl:text>\colon </xsl:text>
			<xsl:apply-templates select="mml:condition/node()"/>
		</xsl:when>
		<xsl:otherwise>
			<xsl:for-each select="*">
				<xsl:apply-templates select="."/>
				<xsl:if test="position()!=last()"><xsl:text>, </xsl:text></xsl:if>
			</xsl:for-each>
		</xsl:otherwise>
   </xsl:choose>
</xsl:template>

<!-- 4.4.6.3 union -->
<xsl:template match="mml:apply[*[1][self::mml:union]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\cup </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.4 intersect -->
<xsl:template match="mml:apply[*[1][self::mml:intersect]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="3"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\cap </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.5 in -->
<xsl:template match="mml:apply[*[1][self::mml:in]] | mml:reln[*[1][self::mml:in]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo">\in </xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="3"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.6 notin -->
<xsl:template match="mml:apply[*[1][self::mml:notin]] | mml:reln[*[1][self::mml:notin]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="mo">\notin </xsl:with-param>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="this-p" select="3"/>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.7 subset -->
<xsl:template match="mml:apply[*[1][self::mml:subset]] | mml:reln[*[1][self::mml:subset]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\subseteq </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.8 prsubset -->
<xsl:template match="mml:apply[*[1][self::mml:prsubset]] | mml:reln[*[1][self::mml:prsubset]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\subset </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.9 notsubset -->
<xsl:template match="mml:apply[*[1][self::mml:notsubset]] | mml:reln[*[1][self::mml:notsubset]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\nsubseteq </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.10 notprsubset -->
<xsl:template match="mml:apply[*[1][self::mml:notprsubset]] | mml:reln[*[1][self::mml:notprsubset]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\not\subset </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.11 setdiff -->
<xsl:template match="mml:apply[*[1][self::mml:setdiff]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\setminus </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.6.12 card -->
<xsl:template match="mml:apply[*[1][self::mml:card]]">
	<xsl:text>|</xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>|</xsl:text>
</xsl:template>

<!-- 4.4.6.13 cartesianproduct 4.4.10.6 vectorproduct -->
<xsl:template match="mml:apply[*[1][self::mml:cartesianproduct or self::mml:vectorproduct]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\times </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<xsl:template
match="mml:apply[*[1][self::mml:cartesianproduct][count(following-sibling::mml:reals)=count(following-sibling::*)]]"
priority="2">
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="5"/>
	</xsl:apply-templates>
	<xsl:text>^{</xsl:text>
	<xsl:value-of select="count(*)-1"/>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.7.1 sum -->
<xsl:template match="mml:apply[*[1][self::mml:sum]]">
	<xsl:text>\sum</xsl:text><xsl:call-template name="series"/>
</xsl:template>

<!-- 4.4.7.2 product -->
<xsl:template match="mml:apply[*[1][self::mml:product]]">
	<xsl:text>\prod</xsl:text><xsl:call-template name="series"/>
</xsl:template>
	
<xsl:template name="series">
	<xsl:if test="mml:lowlimit/*|mml:interval/*[1]|mml:condition/*">
		<xsl:text>_{</xsl:text>
		<xsl:if test="not(mml:condition)">
			<xsl:apply-templates select="mml:bvar"/>
		<!--	<xsl:text>=</xsl:text>-->
		</xsl:if>
		<xsl:apply-templates select="mml:lowlimit/*|mml:interval/*[1]|mml:condition/*"/>
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:if test="mml:uplimit/*|mml:interval/*[2]">
		<xsl:text>^{</xsl:text>
		<xsl:apply-templates select="mml:uplimit/*|mml:interval/*[2]"/>
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="*[last()]"/>
</xsl:template>

<!-- 4.4.7.3 limit -->
<xsl:template match="mml:apply[*[1][self::mml:limit]]">
	<xsl:text>\lim_{</xsl:text>
	<xsl:apply-templates select="mml:lowlimit|mml:condition/*"/>
	<xsl:text>}</xsl:text>
	<xsl:apply-templates select="*[last()]"/>
</xsl:template>

<xsl:template match="mml:apply[mml:limit]/mml:lowlimit" priority="3">
	<xsl:apply-templates select="../mml:bvar/node()"/>
	<xsl:text>\to </xsl:text>
	<xsl:apply-templates/>
</xsl:template>

<!-- 4.4.7.4 tendsto -->
<xsl:template match="mml:apply[*[1][self::mml:tendsto]] | mml:reln[*[1][self::mml:tendsto]]">
	<xsl:param name="p"/>
	<xsl:call-template name="binary">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">
			<xsl:choose>
				<xsl:when test="*[1][@type='above']">\searrow </xsl:when>
				<xsl:when test="*[1][@type='below']">\nearrow </xsl:when>
				<xsl:when test="*[1][@type='two-sided']">\rightarrow </xsl:when>
				<xsl:otherwise>\to </xsl:otherwise>
			</xsl:choose>
		</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.8.1 common tringonometric functions 4.4.8.3 natural logarithm -->
<xsl:template match="mml:apply[*[1][
 self::mml:sin or 		self::mml:cos or 	self::mml:tan or		self::mml:sec or
 self::mml:csc or 		self::mml:cot or 	self::mml:sinh or	 	self::mml:cosh or
 self::mml:tanh or 		self::mml:coth or	self::mml:arcsin or 	self::mml:arccos or
 self::mml:arctan or 	self::mml:ln]]">
	<xsl:text>\</xsl:text>
	<xsl:value-of select="local-name(*[1])"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="mml:sin | mml:cos | mml:tan | mml:sec | mml:csc |
								 mml:cot | mml:sinh | mml:cosh | mml:tanh | mml:coth |
								 mml:arcsin | mml:arccos | mml:arctan | mml:ln">
	<xsl:text>\</xsl:text>
	<xsl:value-of select="local-name(.)"/>
	<xsl:text> </xsl:text>
</xsl:template>

<xsl:template match="mml:apply[*[1][
 self::mml:sech or 		self::mml:csch or		self::mml:arccosh or
 self::mml:arccot or 	self::mml:arccoth or 	self::mml:arccsc or
 self::mml:arccsch or self::mml:arcsec or 	self::mml:arcsech or
 self::mml:arcsinh or self::mml:arctanh]]">
	<xsl:text>\mathrm{</xsl:text>
	<xsl:value-of select="local-name(*[1])"/>
	<xsl:text>\,}</xsl:text>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="mml:sech | mml:csch | mml:arccosh | mml:arccot |
								 mml:arccoth | mml:arccsc |mml:arccsch |mml:arcsec |
								 mml:arcsech | mml:arcsinh | mml:arctanh">
	<xsl:text>\mathrm{</xsl:text>
	<xsl:value-of select="local-name(.)"/>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.8.2 exp -->
<xsl:template match="mml:apply[*[1][self::mml:exp]]">
	<xsl:text>e^{</xsl:text><xsl:apply-templates select="*[2]"/><xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.8.4 log -->
<xsl:template match="mml:apply[*[1][self::mml:log]]">
	<xsl:text>\lg </xsl:text>
	<xsl:apply-templates select="*[last()]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="mml:apply[*[1][self::mml:log] and mml:logbase != 10]">
	<xsl:text>\log_{</xsl:text>
	<xsl:apply-templates select="mml:logbase/node()"/>
	<xsl:text>}</xsl:text>
	<xsl:apply-templates select="*[last()]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<!-- 4.4.9.1 mean -->
<xsl:template match="mml:apply[*[1][self::mml:mean]]">
	<xsl:text>\langle </xsl:text>
	<xsl:for-each select="*[position()&gt;1]">
		<xsl:apply-templates select="."/>
		<xsl:if test="position() !=last()"><xsl:text>, </xsl:text></xsl:if>
	</xsl:for-each>
	<xsl:text>\rangle </xsl:text>
</xsl:template>

<!-- 4.4.9.2 sdef -->
<xsl:template match="mml:sdev"><xsl:text>\sigma </xsl:text></xsl:template>

<!-- 4.4.9.3 variance -->
<xsl:template match="mml:apply[*[1][self::mml:variance]]">
	<xsl:text>\sigma(</xsl:text>
	<xsl:apply-templates select="*[2]"/>
	<xsl:text>)^2</xsl:text>
</xsl:template>

<!-- 4.4.9.5 moment -->
<xsl:template match="mml:apply[*[1][self::mml:moment]]">
	<xsl:text>\langle </xsl:text>
	<xsl:apply-templates select="*[last()]"/>
	<xsl:text>^{</xsl:text>
	<xsl:apply-templates select="mml:degree/node()"/>
	<xsl:text>}\rangle</xsl:text>
	<xsl:if test="mml:momentabout">
		<xsl:text>_{</xsl:text>
		<xsl:apply-templates select="mml:momentabout/node()"/>
		<xsl:text>}</xsl:text>
	</xsl:if>
	<xsl:text> </xsl:text>
</xsl:template>

<!-- 4.4.10.1 vector  -->
<xsl:template match="mml:vector">
	<xsl:text>\left(\begin{array}{c}</xsl:text>
	<xsl:for-each select="*">
		<xsl:apply-templates select="."/>
		<xsl:if test="position()!=last()"><xsl:text>\\ </xsl:text></xsl:if>
	</xsl:for-each>
	<xsl:text>\end{array}\right)</xsl:text>
</xsl:template>

<!-- 4.4.10.2 matrix  -->
<xsl:template match="mml:matrix">
	<xsl:text>\begin{pmatrix}</xsl:text>
	<xsl:apply-templates/>
	<xsl:text>\end{pmatrix}</xsl:text>
</xsl:template>

<!-- 4.4.10.3 matrixrow  -->
<xsl:template match="mml:matrixrow">
	<xsl:for-each select="*">
		<xsl:apply-templates select="."/>
		<xsl:if test="position()!=last()"><xsl:text> &amp; </xsl:text></xsl:if>
	</xsl:for-each>
	<xsl:if test="position()!=last()"><xsl:text>\\ </xsl:text></xsl:if>
</xsl:template>

<!-- 4.4.10.4 determinant  -->
<xsl:template match="mml:apply[*[1][self::mml:determinant]]">
	<xsl:text>\det </xsl:text>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="mml:apply[*[1][self::mml:determinant]][*[2][self::mml:matrix]]" priority="2">
	<xsl:text>\begin{vmatrix}</xsl:text>
	<xsl:apply-templates select="mml:matrix/*"/>
	<xsl:text>\end{vmatrix}</xsl:text>
</xsl:template>

<!-- 4.4.10.5 transpose -->
<xsl:template match="mml:apply[*[1][self::mml:transpose]]">
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
	<xsl:text>^T</xsl:text>
</xsl:template>

<!-- 4.4.10.6 selector -->
<xsl:template match="mml:apply[*[1][self::mml:selector]]">
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="7"/>
	</xsl:apply-templates>
	<xsl:text>_{</xsl:text>
	<xsl:for-each select="*[position()&gt;2]">
		<xsl:apply-templates select="."/>
		<xsl:if test="position() !=last()"><xsl:text>, </xsl:text></xsl:if>
	</xsl:for-each>
	<xsl:text>}</xsl:text>
</xsl:template>

<!-- 4.4.10.8 scalarproduct -->
<xsl:template match="mml:apply[*[1][self::mml:scalarproduct]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\cdot </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.10.9 outerproduct -->
<xsl:template match="mml:apply[*[1][self::mml:outerproduct]]">
	<xsl:param name="p" select="0"/>
	<xsl:call-template name="infix">
		<xsl:with-param name="this-p" select="2"/>
		<xsl:with-param name="p" select="$p"/>
		<xsl:with-param name="mo">\otimes </xsl:with-param>
	</xsl:call-template>
</xsl:template>

<!-- 4.4.11.2 semantics -->
<xsl:template match="mml:semantics"><xsl:apply-templates select="*[1]"/></xsl:template>

<xsl:template match="mml:semantics[mml:annotation/@encoding='TeX']">
	<xsl:apply-templates select="mml:annotation[@encoding='TeX']/node()"/>
</xsl:template>

<!-- 4.4.12.1 integers -->
<xsl:template match="mml:integers"><xsl:text>\mathbb{Z}</xsl:text></xsl:template>

<!-- 4.4.12.2 reals -->
<xsl:template match="mml:reals"><xsl:text>\mathbb{R}</xsl:text></xsl:template>

<!-- 4.4.12.3 rationals -->
<xsl:template match="mml:rationals"><xsl:text>\mathbb{Q}</xsl:text></xsl:template>

<!-- 4.4.12.4 naturalnumbers -->
<xsl:template match="mml:naturalnumbers"><xsl:text>\mathbb{N}</xsl:text></xsl:template>

<!-- 4.4.12.5 complexes -->
<xsl:template match="mml:complexes"><xsl:text>\mathbb{C}</xsl:text></xsl:template>

<!-- 4.4.12.6 primes -->
<xsl:template match="mml:primes"><xsl:text>\mathbb{P}</xsl:text></xsl:template>
	
<!-- 4.4.12.7 exponentiale -->
<xsl:template match="mml:exponentiale"><xsl:text>e</xsl:text></xsl:template>

<!-- 4.4.12.8 imaginaryi -->
<xsl:template match="mml:imaginaryi"><xsl:text>i</xsl:text></xsl:template>

<!-- 4.4.12.9 notanumber -->
<xsl:template match="mml:notanumber"><xsl:text>NaN</xsl:text></xsl:template>

<!-- 4.4.12.10 true -->
<xsl:template match="mml:true"><xsl:text>\mbox{true}</xsl:text></xsl:template>

<!-- 4.4.12.11 false -->
<xsl:template match="mml:false"><xsl:text>\mbox{false}</xsl:text></xsl:template>

<!-- 4.4.12.12 emptyset -->
<xsl:template match="mml:emptyset"><xsl:text>\emptyset </xsl:text></xsl:template>

<!-- 4.4.12.13 pi -->
<xsl:template match="mml:pi"><xsl:text>\pi </xsl:text></xsl:template>

<!-- 4.4.12.14 eulergamma -->
<xsl:template match="mml:eulergamma"><xsl:text>\gamma </xsl:text></xsl:template>

<!-- 4.4.12.15 infinity -->
<xsl:template match="mml:infinity"><xsl:text>\infty </xsl:text></xsl:template>

<!-- ****************************** -->
<xsl:template name="infix" >
  <xsl:param name="mo"/>
  <xsl:param name="p" select="0"/>
  <xsl:param name="this-p" select="0"/>
  <xsl:if test="$this-p &lt; $p"><xsl:text>(</xsl:text></xsl:if>
  <xsl:for-each select="*[position()&gt;1]">
		<xsl:if test="position() &gt; 1">
			<xsl:copy-of select="$mo"/>
		</xsl:if>   
		<xsl:apply-templates select=".">
			<xsl:with-param name="p" select="$this-p"/>
		</xsl:apply-templates>
	</xsl:for-each>
  <xsl:if test="$this-p &lt; $p"><xsl:text>)</xsl:text></xsl:if>
</xsl:template>

<xsl:template name="binary" >
  <xsl:param name="mo"/>
  <xsl:param name="p" select="0"/>
  <xsl:param name="this-p" select="0"/>
  <xsl:if test="$this-p &lt; $p"><xsl:text>(</xsl:text></xsl:if>
	<xsl:apply-templates select="*[2]">
		<xsl:with-param name="p" select="$this-p"/>
	</xsl:apply-templates>
	<xsl:value-of select="$mo"/>
	<xsl:apply-templates select="*[3]">
    	<xsl:with-param name="p" select="$this-p"/>
	</xsl:apply-templates>
	<xsl:if test="$this-p &lt; $p"><xsl:text>)</xsl:text></xsl:if>
</xsl:template>

</xsl:stylesheet>