<!-- ******************************************************** -->
<!--  XSL Transform of MathML content to MathML presentation  -->
<!--             Version 1.0 RC2 from 13-Jun-2003             -->
<!--                                                          -->
<!--    Complies with the W3C MathML 2.0 Recommenation of     -->
<!--                    21 February 2001.                     -->
<!--                                                          -->
<!--   Authors Igor Rodionov <igor@csd.uwo.ca>,               -->
<!--           Stephen Watt  <watt@csd.uwo.ca>.               -->
<!--                                                          -->
<!-- (C) Copyright 2000-2003 Symbolic Computation Laboratory, -->
<!--                         University of Western Ontario,   -->
<!--                         London, Canada N6A 5B7.          -->
<!-- ******************************************************** -->
<xsl:stylesheet id="mml2brl.xsl"
                version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns="http://www.w3.org/1998/Math/MathML">


  <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

  <xsl:strip-space elements="apply semantics annotation-xml
        csymbol fn cn ci interval matrix matrixrow vector
        lambda bvar condition logbase degree set list
        lowlimit uplimit math"/>

	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<!--         Parameters, variables and constants           -->
	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<!-- ~~~~~~~~ Semantics related *constants*: ~~~~~~~~ -->
	<!-- Strip off semantics -->
	<xsl:variable name="SEM_STRIP" select="-1"/>
	<!-- Pass semantics "as is" -->
	<xsl:variable name="SEM_PASS" select="0"/>
	<!-- Add semantics at top level only -->
	<xsl:variable name="SEM_TOP" select="1"/>
	<!-- Add semantics at all levels -->
	<xsl:variable name="SEM_ALL" select="2"/>
	<!-- Semantics at top level only, with id refs -->
	<!-- NOTE: ids have to be already present in the
           input for this feature to work. -->
	<xsl:variable name="SEM_XREF" select="3"/>
	<!-- No semantics at top level, with id refs -->
	<!-- NOTE: ids have to be already present in the
           input for this feature to work. -->
	<xsl:variable name="SEM_XREF_EXT" select="4"/>
	<!-- ~~~~~~~~~~ Stylesheet *parameter*: SEM_SW ~~~~~~~~~~~~~~ -->
	<!-- Assumes one of the above values; SEM_PASS is the default -->
	<!-- The default can be overridden by specifying different    -->
	<!-- value on the command line when the stylesheet is invoked -->
	<xsl:param name="SEM_SW" select="SEM_PASS"/>
	<!-- ~~~~~~ Operator precedence definitions ~~~~~~ -->
	<xsl:variable name="NO_PREC" select="0"/>
	<xsl:variable name="UNION_PREC" select="1"/>
	<xsl:variable name="SETDIFF_PREC" select="1"/>
	<xsl:variable name="INTERSECT_PREC" select="3"/>
	<xsl:variable name="CARTPROD_PREC" select="3"/>
	<xsl:variable name="OR_PREC" select="5"/>
	<xsl:variable name="XOR_PREC" select="7"/>
	<xsl:variable name="AND_PREC" select="9"/>
	<xsl:variable name="NOT_PREC" select="11"/>
	<xsl:variable name="PLUS_PREC" select="13"/>
	<xsl:variable name="MINUS_PREC" select="13"/>
	<xsl:variable name="NEG_PREC" select="15"/>
	<xsl:variable name="MUL_PREC" select="17"/>
	<xsl:variable name="DIV_PREC" select="17"/>
	<xsl:variable name="REM_PREC" select="17"/>
	<xsl:variable name="FUNCTN_PREC" select="97"/>
	<xsl:variable name="GEN_FUN_PREC" select="99"/>
	<!-- ~~~~~ Miscellaneous constant definitions ~~~~~ -->
	<xsl:variable name="YES" select="1"/>
	<xsl:variable name="NO" select="0"/>
	<xsl:variable name="NO_PARAM" select="-1"/>
	<xsl:variable name="PAR_SAME" select="-3"/>
	<xsl:variable name="PAR_YES" select="-5"/>
	<xsl:variable name="PAR_NO" select="-7"/>
	<!-- +++++++++++++++++ INDEX OF TEMPLATES +++++++++++++++++++ -->
	<!-- All templates are subdivided into the following categories
     (listed in the order of appearance in the stylesheet):

THE TOPMOST ELEMENT: MATH
 math

SEMANTICS HANDLING
 semantics

BASIC CONTAINER ELEMENTS
 cn, ci; csymbol

BASIC CONTENT ELEMENTS
 fn, interval, inverse, sep, condition, declare, lambda, compose,
 ident; domain, codomain, image, domainofapplication, piecewise

ARITHMETIC, ALGEBRA & LOGIC
 quotient, exp, factorial, max, min, minus, plus, power, rem, divide,
 times, root, gcd, and, or, xor, not, forall, exists, abs, conjugate;
 arg, real, imaginary, lcm, floor, ceiling

RELATIONS
 neq, approx, tendsto, implies, in, notin, notsubset, notprsubset,
 subset, prsubset, eq, gt, lt, geq, leq; equivalent, factorof

CALCULUS
 ln, log, diff, partialdiff, lowlimit, uplimit, bvar, degree,
 logbase; divergence, grad, curl, laplacian

SET THEORY
 set, list, union, intersect, setdiff; card, cartesianproduct

SEQUENCES AND SERIES
 sum, product, limit

TRIGONOMETRY
 sin, cos, tan, sec, csc, cot, sinh, cosh, tanh, sech, csch, coth,
 arcsin, arccos, arctan, arcsec, arccsc, arccot, arcsinh, arccosh,
 arctanh, arcsech, arccsch, arccoth

STATISTICS
 mean, sdev, variance, median, mode, moment, momentabout

LINEAR ALGEBRA
 vector, matrix, matrixrow, determinant, transpose, selector;
 vectorproduct, scalarproduct, outerproduct

CONSTANT and SYMBOL ELEMENTS
 integers, reals, rationals, naturalnumbers, complexes, primes,
 exponentiale, imaginaryi, notanumber, true, false, emptyset,
 pi, eulergamma, infinity
-->
	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~ TEMPLATES ~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	<!-- *********************** COPY THROUGH ************************ -->
	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	<!-- ***************** THE TOPMOST ELEMENT: MATH ***************** -->
	<xsl:template match="math">
		<xsl:copy-of select="@*"/>
		<xsl:choose>
			<xsl:when test="$SEM_SW=$SEM_TOP or $SEM_SW=$SEM_ALL and *[2] or
	                  $SEM_SW=$SEM_XREF">
						<xsl:apply-templates mode="semantics"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates mode="semantics"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
    <xsl:template match="mstyle">
        <xsl:choose>
            <xsl:when test="$SEM_SW=$SEM_TOP or $SEM_SW=$SEM_ALL and *[2] or
	                  $SEM_SW=$SEM_XREF">
                <xsl:apply-templates mode="semantics"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:apply-templates mode="semantics"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
	<!-- ***************** SEMANTICS HANDLING ***************** -->
	<!-- This template is called recursively.  At each level   -->
	<!-- in the source tree it decides whether to strip off,   -->
	<!-- pass or add semantics at that level (depending on the -->
	<!-- value of SEM_SW parameter).  Then the actual template -->
	<!-- is applied to the node.                               -->
	<xsl:template match="*" mode="semantics">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$SEM_SW=$SEM_STRIP and self::semantics">
				<xsl:apply-templates select="annotation-xml[@encoding='MathML']">
					<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="($SEM_SW=$SEM_PASS or $SEM_SW=$SEM_TOP) and self::semantics">

					<xsl:apply-templates select="*[1]">
						<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
					<xsl:copy-of select="annotation-xml"/>

			</xsl:when>
			<xsl:when test="$SEM_SW=$SEM_ALL">

					<xsl:choose>
						<xsl:when test="self::semantics">
							<xsl:apply-templates select="*[1]">
								<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
								<xsl:with-param name="PARAM" select="$PARAM"/>
								<xsl:with-param name="PAREN" select="$PAREN"/>
								<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
							</xsl:apply-templates>
							<xsl:copy-of select="annotation-xml"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:apply-templates select=".">
								<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
								<xsl:with-param name="PARAM" select="$PARAM"/>
								<xsl:with-param name="PAREN" select="$PAREN"/>
								<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
							</xsl:apply-templates>

						</xsl:otherwise>
					</xsl:choose>

			</xsl:when>
			<xsl:when test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:choose>
					<xsl:when test="self::sematics">
						<xsl:copy>
							<xsl:copy-of select="@*"/>
							<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
							<xsl:copy-of select="*[1]"/>
							<xsl:copy-of select="annotation-xml"/>
						</xsl:copy>
					</xsl:when>
					<xsl:otherwise>
						<xsl:apply-templates select=".">
							<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
							<xsl:with-param name="PARAM" select="$PARAM"/>
							<xsl:with-param name="PAREN" select="$PAREN"/>
							<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
						</xsl:apply-templates>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select=".">
					<xsl:with-param name="IN_PREC" select="$IN_PREC"/>
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="semantics">
		<xsl:apply-templates select="*[1]" mode="semantics"/>
	</xsl:template>
	<!-- ***************** BASIC CONTAINER ELEMENTS ***************** -->
	<xsl:template match="cn">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test=". &lt; 0 and $IN_PREC &gt; $NO_PREC and $PAREN=$PAR_NO
                                                   and $PAR_NO_IGNORE=$NO">
					<xsl:apply-templates select="." mode="cn"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates select="." mode="cn"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="cn" mode="cn">
		<xsl:choose>
			<xsl:when test="not(@type) or @type='integer' or @type='real'">
				<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
					<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
				</xsl:if>
                <xsl:text>#</xsl:text>
                <xsl:value-of select="translate(.,'.1234567890','1ABCDEFGHIJ')" />

			</xsl:when>

			<xsl:when test="@type='complex-cartesian' and not(@base) and child::sep[1]">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>

                    <xsl:text>#</xsl:text>
                    <xsl:value-of select="translate(text()[1],'.1234567890','1ABCDEFGHIJ')" />

					<xsl:if test="text()[2] &lt; 0">
                        <xsl:text> -</xsl:text>
                        <xsl:value-of select="translate(-text()[2],'.1234567890','1ABCDEFGHIJ')" />
					</xsl:if>

					<xsl:if test="not(text()[2] &lt; 0)">
                        <xsl:text> 6</xsl:text>
                        <xsl:value-of select="translate(text()[2],'.1234567890','1ABCDEFGHIJ')" />
					</xsl:if>

                    <xsl:text>;I</xsl:text>

			</xsl:when>

			<xsl:when test="@type='rational' and not(@base) and child::sep[1]">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
                    <xsl:text>#</xsl:text>
                    <xsl:value-of select="translate(text()[1],'.1234567890','1ABCDEFGHIJ')" />
                    <xsl:value-of select="translate(text()[2],'1234567890','1234567890')" />

			</xsl:when>

			<xsl:otherwise>
					<xsl:apply-templates mode="semantics"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="ci">
		<xsl:choose>
			<xsl:when test="@type='vector' or @type='matrix' or @type='set'">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
                    <xsl:if test="contains('ABCDEFGHIJKLMNOPQRSTUVWXYZ',.)">
                        <xsl:text>,</xsl:text>
                        <xsl:value-of select="translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
                    </xsl:if>
                    <xsl:if test="contains('abcdefghijklmnopqrstuvwxyz',.)">
                        <xsl:text>;</xsl:text>
                        <xsl:value-of select="translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
                    </xsl:if>
            </xsl:when>
			<xsl:when test="child::text() and not(child::*[1])">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
                    <xsl:if test="contains('ABCDEFGHIJKLMNOPQRSTUVWXYZ',.)">
                        <xsl:text>,</xsl:text>
                        <xsl:value-of select="translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
                    </xsl:if>
                    <xsl:if test="contains('abcdefghijklmnopqrstuvwxyz',.)">
                        <xsl:text>;</xsl:text>
                        <xsl:value-of select="translate(.,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
                    </xsl:if>

			</xsl:when>
			<xsl:when test="child::text() and *[1] and not(*[1]=sep)">
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:if test="*[2]">
						<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
							<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
						</xsl:if>
						<xsl:apply-templates select="*"/>
				</xsl:if>
				<xsl:if test="not(*[2])">
					<xsl:apply-templates select="*[1]"/>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="ci/*[not(self::sep)]">
		<xsl:copy-of select="."/>
	</xsl:template>
	<!-- ***************** BASIC CONTENT ELEMENTS ***************** -->
	<!-- General <apply> <AnyFunction/> ... </apply> -->
	<!-- Dependants: csymbol apply[fn inverse compose] -->
	<xsl:template match="apply">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="*[1]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
				<xsl:with-param name="PARAM" select="$PAR_SAME"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>

            <xsl:if test="*[1]=fn">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>

			<xsl:apply-templates select="*[position()>1]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
				<xsl:with-param name="PARAM" select="$PAR_SAME"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>

            <xsl:if test="*[1]=fn">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>

	</xsl:template>
	<!-- fn is ***DEPRECATED*** -->
	<xsl:template match="fn">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates mode="semantics">
			<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
			<xsl:with-param name="PARAM" select="$PAR_SAME"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="condition">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="*" mode="semantics"/>

	</xsl:template>

	<xsl:template match="piecewise">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:text>['</xsl:text>

			<xsl:for-each select="piece">
				<xsl:apply-templates select="*[position()=1]" mode="semantics"/>
                <xsl:text>  </xsl:text>
			    <xsl:apply-templates select="*[position()=2]" mode="semantics"/>
                <xsl:text>.\</xsl:text>
			</xsl:for-each>
				<xsl:if test="otherwise">
				<xsl:apply-templates select="otherwise/*" mode="semantics"/>
				</xsl:if>

	</xsl:template>
	<!-- ***************** ARITHMETIC, ALGEBRA & LOGIC ***************** -->

	<xsl:template match="apply[*[1][self::exp]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:text>;E/</xsl:text>
			<xsl:apply-templates select="*[2]" mode="semantics"/>
            <xsl:text>:</xsl:text>
	</xsl:template>
	<xsl:template match="apply[factorial[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
			<xsl:apply-templates select="*[2]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
            <xsl:text>,6</xsl:text>

	</xsl:template>

	<xsl:template match="apply[minus[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $MINUS_PREC or $IN_PREC=$MINUS_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="minus">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="minus">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="minus">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[minus[1]]" mode="minus">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
        <xsl:if test="not(*[3])">
            <xsl:text> -</xsl:text>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
            <xsl:apply-templates select="*[2]" mode = "semantics">
                <xsl:with-param name="IN_PREC" select="$NEG_PREC"/>
                <xsl:with-param name="PAREN" select="$PAREN"/>
                <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
            </xsl:apply-templates>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
        </xsl:if>
        <xsl:if test="*[3]">
            <xsl:apply-templates select="*[2]" mode = "semantics">
                <xsl:with-param name="IN_PREC" select="$MINUS_PREC"/>
                <xsl:with-param name="PARAM" select="$PARAM"/>
                <xsl:with-param name="PAREN" select="$PAREN"/>
                <xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
            </xsl:apply-templates>
            <xsl:text> -</xsl:text>
            <xsl:if test="*[3]=apply[plus[1]|minus[1]]">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
            <xsl:apply-templates select="*[3]" mode = "semantics">
                <xsl:with-param name="IN_PREC" select="$MINUS_PREC"/>
                <xsl:with-param name="PARAM" select="$PAR_SAME"/>
                <xsl:with-param name="PAREN" select="$PAREN"/>
                <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
            </xsl:apply-templates>
            <xsl:if test="*[3]=apply[plus[1]|minus[1]]">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
        </xsl:if>

	</xsl:template>
	<xsl:template match="apply[plus[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $PLUS_PREC or $IN_PREC=$PLUS_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>

					<xsl:apply-templates select="." mode="plus">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="plus">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>

					<xsl:apply-templates select="." mode="plus">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[plus[1]]" mode="plus">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:if test="*[2]">
			<xsl:apply-templates select="*[2]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$PLUS_PREC"/>
				<xsl:with-param name="PARAM" select="$PARAM"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
			</xsl:apply-templates>
			<xsl:for-each select="*[position()>2]">
				<xsl:choose>
					<xsl:when test=". &lt; 0">
                        <xsl:text> -</xsl:text>
                        <xsl:text>#</xsl:text>
                        <xsl:value-of select="translate(-.,'.1234567890','1ABCDEFGHIJ')" />
					</xsl:when>
					<xsl:when test="self::apply[minus[1]] and not(*[3])">
						<xsl:apply-templates select="." mode="semantics">
							<xsl:with-param name="IN_PREC" select="$PLUS_PREC"/>
							<xsl:with-param name="PAREN" select="$PAREN"/>
							<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
						</xsl:apply-templates>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text> 6</xsl:text>
						<xsl:apply-templates select="." mode="semantics">
							<xsl:with-param name="IN_PREC" select="$PLUS_PREC"/>
							<xsl:with-param name="PAREN" select="$PAREN"/>
							<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
						</xsl:apply-templates>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	<xsl:template match="apply[*[1][self::power]]">
		<xsl:choose>
			<xsl:when test="*[2]=apply[ln[1] | log[1] | abs[1] |
                         gcd[1] | lcm[1] | sin[1] | cos[1] | tan[1] |
                         sec[1] | csc[1] | cot[1] | sinh[1] |
                         cosh[1] | tanh[1] | sech[1] | csch[1] |
                         coth[1] | arcsin[1] | arccos[1] |
                         arctan[1] | arcsec[1] | arccsc[1] |
                         arccot[1] | arcsinh[1] | arccosh[1] |
                         arctanh[1] | arcsech[1] | arccsch [1] |
                         arccoth[1]]">
				<xsl:apply-templates select="*[2]" mode="semantics"/>
			</xsl:when>
			<xsl:otherwise>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="*[2]" mode="semantics">
						<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
					</xsl:apply-templates>
                    <xsl:text>/</xsl:text>
                    <xsl:if test="not(*[3]=cn)">
                        <xsl:if test="name(*[3]/child::node()[1])='divide' or name(*[3]/child::node()[2]/child::node()[1])='divide'">
                            <xsl:text>,</xsl:text>
                        </xsl:if>
                        <xsl:apply-templates select="*[3]" mode="semantics"/>
                        <xsl:text>:</xsl:text>
                    </xsl:if>
                    <xsl:if test="*[3]=cn">
                        <xsl:value-of select="translate(*[3],'1234567890','1234567890')" />
                    </xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[divide[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $DIV_PREC or $IN_PREC=$DIV_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="div">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
                    </xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="div">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="div">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[divide[1]]" mode="div">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
        <xsl:if test="*[2]=apply or *[3]=apply">
            <xsl:text>2</xsl:text>
        </xsl:if>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$GEN_FUN_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>

        <xsl:choose>
            <xsl:when test="*[2]=cn and *[3]=cn">
                <xsl:text> 4</xsl:text>
            </xsl:when>
            <xsl:when test="*[2]=ci or *[3]=ci">
                <xsl:text>\</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text> \</xsl:text>
            </xsl:otherwise>
        </xsl:choose>


		<xsl:apply-templates select="*[3]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$GEN_FUN_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
        <xsl:if test="*[2]=apply or *[3]=apply">
        <xsl:text>;</xsl:text>
        </xsl:if>

	</xsl:template>

	<xsl:template match="apply[times[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $MUL_PREC or $IN_PREC=$MUL_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="times">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="times">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="times">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[times[1]]" mode="times">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$MUL_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>

        <xsl:for-each select = "*[position()>2]">
            <xsl:if test="name(.)='cn'">
                <xsl:text>'</xsl:text>
            </xsl:if>
            <xsl:if test="./child::node()[1]=plus or ./child::node()[1]=minus">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
            <xsl:apply-templates select="." mode = "semantics">
                <xsl:with-param name="IN_PREC" select="$MUL_PREC"/>
                <xsl:with-param name="PAREN" select="$PAREN"/>
                <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
            </xsl:apply-templates>
            <xsl:if test="./child::node()[1]=plus or ./child::node()[1]=minus">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
        </xsl:for-each>

	</xsl:template>
	<xsl:template match="apply[*[1]=root and *[2]=degree]">
            <xsl:text>%</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:if test="name(*[2]/child::node()[1])='cn' and not(translate(*[2],'1234567890','1234567890')=2)">
                    <xsl:value-of select="translate(*[2],'1234567890','1234567890')" />
            </xsl:if>
            <xsl:if test="not(name(*[2]/child::node()[1])='cn')">
                <xsl:apply-templates select="*[2]" mode="semantics"/>
            </xsl:if>
            <xsl:text>:</xsl:text>
            <xsl:apply-templates select="*[3]" mode="semantics">
               <xsl:with-param name="IN_PREC" select="$GEN_FUN_PREC"/>
               <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
            </xsl:apply-templates>
            <xsl:text>?</xsl:text>
	</xsl:template>
	<xsl:template match="apply[*[1]=root and not(*[2]=degree)]">
    <xsl:text>%</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
      <xsl:text>:</xsl:text>
			<xsl:apply-templates select="*[2]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$GEN_FUN_PREC"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
        <xsl:text>?</xsl:text>
	</xsl:template>

	<xsl:template match="apply[and[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $AND_PREC or $IN_PREC=$AND_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="and">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="and">
					<xsl:with-param name="PARAM" select="$IN_PREC"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="and">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[and[1]]" mode="and">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$AND_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
        <xsl:text> ;5</xsl:text>
		<xsl:for-each select="*[position()>2]">
			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$AND_PREC"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[or[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $OR_PREC or $IN_PREC=$OR_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="or">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="or">
					<xsl:with-param name="PARAM" select="$IN_PREC"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="or">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[or[1]]" mode="or">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$OR_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
        <xsl:text> ;9</xsl:text>
		<xsl:for-each select="*[position()>2]">
			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$OR_PREC"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[xor[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and
                   ($IN_PREC &gt; $XOR_PREC or $IN_PREC=$XOR_PREC and $PARAM=$PAR_SAME)">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="xor">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAR_YES"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                                                and not($SEM_SW=$SEM_ALL)">
				<xsl:apply-templates select="." mode="xor">
					<xsl:with-param name="PARAM" select="$IN_PREC"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="xor">
						<xsl:with-param name="PARAM" select="$IN_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[xor[1]]" mode="xor">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$XOR_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
        <xsl:text> 6</xsl:text>
		<xsl:for-each select="*[position()>2]">
			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$XOR_PREC"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[not[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &lt; $GEN_FUN_PREC and $IN_PREC &gt;= $NOT_PREC">

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
                    <xsl:text> %</xsl:text>
                <xsl:if test="*[2]=apply">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
                </xsl:if>
                <xsl:apply-templates select="*[2]" mode="semantics">
                    <xsl:with-param name="IN_PREC" select="$NOT_PREC"/>
                    <xsl:with-param name="PAREN" select="$PAREN"/>
                    <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
                </xsl:apply-templates>
                <xsl:if test="*[2]=apply">
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
                </xsl:if>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
                    <xsl:text> %</xsl:text>
                    <xsl:if test="*[2]=apply">
                        <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
                    </xsl:if>
					<xsl:apply-templates select="*[2]" mode="semantics">
						<xsl:with-param name="IN_PREC" select="$NOT_PREC"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
					</xsl:apply-templates>
                    <xsl:if test="*[2]=apply">
                        <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
                    </xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template match="apply[abs[1]]">
		<xsl:if test="not(parent::apply[power[1]])">
                <xsl:text>_</xsl:text>
				<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
					<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
				</xsl:if>
				<xsl:apply-templates select="*[position()>1]" mode="semantics"/>
                <xsl:text>_</xsl:text>
		</xsl:if>
		<xsl:if test="parent::apply[power[1]]">
                <xsl:text>_</xsl:text>
				<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
					<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
				</xsl:if>
		 		<xsl:apply-templates select="*[position()>1]" mode="semantics"/>
                <xsl:text>_</xsl:text>
                <xsl:text>/</xsl:text>
                <xsl:if test="../*[3]">
                    <xsl:value-of select="translate(../*[3],'1234567890','1234567890')" />
                </xsl:if>
                <xsl:text>:</xsl:text>
		</xsl:if>
	</xsl:template>
	<xsl:template match="apply[conjugate[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>

			<xsl:apply-templates select="*[position()>1]" mode="semantics"/>

            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
            <xsl:text>^</xsl:text>
            <xsl:text>3</xsl:text>

	</xsl:template>
	<xsl:template match="apply[arg[1] | real[1] | imaginary[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>

				<xsl:if test="arg">
                    <xsl:text>$A</xsl:text>
				</xsl:if>
				<xsl:if test="real">
                    <xsl:text>$,RE</xsl:text>
				</xsl:if>
				<xsl:if test="imaginary">
                    <xsl:text>$,IM</xsl:text>
				</xsl:if>

            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
			<xsl:apply-templates select="*[2]" mode="semantics"/>
            <xsl:if test="*[2]=apply">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>

	</xsl:template>
	<!-- ***************** RELATIONS ***************** -->
	<xsl:template match="apply[neq | approx | tendsto | implies
                     | in | notin | notsubset | mnotprsubset
                     | subset | prsubset | eq | gt | lt
                     | geq | leq | equivalent | factorof]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="." mode="relations"/>

	</xsl:template>
	<!-- reln is ***DEPRECATED*** -->
	<xsl:template match="reln[neq | approx | tendsto | implies
                     | in | notin | notsubset | notprsubset
                     | subset | prsubset |eq | gt | lt
                     | geq | leq | equivalent | factorof]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="." mode="relations"/>

	</xsl:template>
	<xsl:template match="*" mode="relations">
		<xsl:if test="*[1]=neq or *[1]=approx or *[1]=factorof or *[1]=tendsto or
                *[1]=implies or *[1]=in or *[1]=notin or
                *[1]=notsubset or *[1]=notprsubset">
			<xsl:apply-templates select="*[2]" mode="semantics"/>

				<xsl:if test="*[1]=neq">
					<xsl:text>@7</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=approx">
                    <xsl:text> 55</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=factorof">
                    <xsl:text>_ </xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=tendsto">
                    <xsl:text> 3O</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=implies">
                    <xsl:text> 7></xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=in">
                    <xsl:text> "[ </xsl:text>
                </xsl:if>
				<xsl:if test="*[1]=notin">
                    <xsl:text>^[</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=notsubset">
                    <xsl:text disable-output-escaping="yes">@&amp;</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=notprsubset">
                    <xsl:text disable-output-escaping="yes">@&amp;</xsl:text>
				</xsl:if>

			<xsl:apply-templates select="*[3]" mode="semantics"/>
		</xsl:if>
		<xsl:if test="*[1]=subset or *[1]=prsubset or *[1]=eq or *[1]=gt
             or *[1]=lt or *[1]=geq or *[1]=leq or *[1]=equivalent">
			<xsl:apply-templates select="*[2]" mode="semantics"/>
			<xsl:for-each select="*[position()>2]">

					<xsl:if test="../*[self::subset][1]">
						<xsl:text disable-output-escaping="yes"> &amp;7</xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::prsubset][1]">
                        <xsl:text disable-output-escaping="yes"> &amp; </xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::eq][1]">
                        <xsl:text> 7</xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::gt][1]">
                        <xsl:text> O </xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::lt][1]">
                        <xsl:text> [ </xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::geq][1]">
                        <xsl:text> O7</xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::leq][1]">
                        <xsl:text> [7</xsl:text>
					</xsl:if>
					<xsl:if test="../*[self::equivalent][1]">
                        <xsl:text> ;7</xsl:text>
					</xsl:if>

				<xsl:apply-templates select="." mode="semantics"/>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	<!-- ***************** CALCULUS ***************** -->
	<xsl:template match="apply[*[1][self::ln]]">
            <xsl:text>$LN</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="parent::apply[power[1]]">
                    <xsl:text>/</xsl:text>
                    <xsl:if test="../*[3]">
                        <xsl:value-of select="translate(../*[3],'1234567890','1234567890')" />
                    </xsl:if>
                    <xsl:text>:</xsl:text>
				</xsl:when>
			</xsl:choose>
			<xsl:apply-templates select="*[2]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>

	</xsl:template>
	<xsl:template match="apply[log[1]]">
            <xsl:text>$L</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="parent::apply[power[1]]">
					<xsl:if test="not(*[2]=logbase)">
					</xsl:if>
                    <xsl:text>*</xsl:text>
                    <xsl:if test="*[2]=logbase[*[1]=cn]">
                        <xsl:value-of select="translate(*[2],'1234567890','1234567890')" />
                    </xsl:if>
                    <xsl:if test="not(*[2]=logbase[*[1]=cn])">
                        <xsl:apply-templates select="logbase" mode="semantics"/>
                    </xsl:if>
                    <xsl:text>:</xsl:text>
                    <xsl:text>/</xsl:text>
                    <xsl:if test="../*[3]">
                        <xsl:value-of select="translate(../*[3],'1234567890','1234567890')" />
                    </xsl:if>
                    <xsl:text>:</xsl:text>
				</xsl:when>
				<xsl:otherwise>
                    <xsl:text>*</xsl:text>
                    <xsl:if test="*[2]=logbase[*[1]=cn]">
                        <xsl:value-of select="translate(*[2],'1234567890','1234567890')" />
                    </xsl:if>
					<xsl:if test="not(*[2]=logbase[*[1]=cn])">
							<xsl:apply-templates select="logbase" mode="semantics"/>
                        <xsl:text>:</xsl:text>
					</xsl:if>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:if test="*[2]=logbase">
                <xsl:if test="*[3]=apply[divide[1]]">
                    <xsl:text>,</xsl:text>
                </xsl:if>
                <xsl:if test="*[3]=apply[plus[1]|minus[1]]">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
                </xsl:if>
				<xsl:apply-templates select="*[3]" mode="semantics">
					<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
				</xsl:apply-templates>
                <xsl:if test="*[3]=apply[plus[1]|minus[1]]">
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
                </xsl:if>
			</xsl:if>
	</xsl:template>
	<xsl:template match="apply[diff[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="bvar">
					<xsl:if test="not(bvar[*[2]=degree])">
                        <xsl:text>2</xsl:text>
                        <xsl:text>;D</xsl:text>
                        <xsl:text>\</xsl:text>
                        <xsl:text>;D</xsl:text>
						<xsl:apply-templates select="bvar/*[1]" mode="semantics"/>
                        <xsl:text>;</xsl:text>
					</xsl:if>
					<xsl:apply-templates select="*[position()=last() and not(self::bvar)]" mode="semantics">
						<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
					</xsl:apply-templates>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*[position()=last() and not(self::bvar)]" mode="semantics">
						<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
					</xsl:apply-templates>
                    <xsl:text>/9</xsl:text>
				</xsl:otherwise>
			</xsl:choose>

	</xsl:template>

	<xsl:template match="lowlimit | uplimit | bvar | degree | logbase">
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="*" mode="semantics"/>
	</xsl:template>
	<xsl:template match="apply[divergence[1] | grad[1] | curl[1]]">
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
				<xsl:if test="*[1]=divergence">
                    <xsl:text>$0'</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=grad">
                    <xsl:text>$0</xsl:text>
				</xsl:if>
				<xsl:if test="*[1]=curl">
                    <xsl:text>$0 8</xsl:text>
				</xsl:if>
			<xsl:choose>
				<xsl:when test="*[2]=ci">
					<xsl:apply-templates select="*[2]" mode="semantics"/>
				</xsl:when>
				<xsl:otherwise>
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
						<xsl:apply-templates select="*[2]" mode="semantics"/>
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[laplacian[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
            <xsl:text>_D</xsl:text>
			<xsl:apply-templates select="*[2]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$GEN_FUN_PREC"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>

	</xsl:template>
	<!-- ***************** SET THEORY ***************** -->

	<xsl:template match="apply[union[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &gt; $UNION_PREC or $IN_PREC=$UNION_PREC
                    and $PARAM=$PAR_SAME">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="union">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="union">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="union">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[union[1]]" mode="union">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$UNION_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
		<xsl:for-each select="*[position()>2]">

				<xsl:text>_#</xsl:text>

			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$UNION_PREC"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[intersect[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &gt; $INTERSECT_PREC or $IN_PREC=$INTERSECT_PREC
                    and $PARAM=$PAR_SAME">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="intersect">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="intersect">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="intersect">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[intersect[1]]" mode="intersect">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$INTERSECT_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
		<xsl:for-each select="*[position()>2]">

            <xsl:text>;?</xsl:text>

			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$INTERSECT_PREC"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[setdiff[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &gt; $SETDIFF_PREC or $IN_PREC=$SETDIFF_PREC
                    and $PARAM=$PAR_SAME">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="setdiff">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="setdiff">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="setdiff">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="apply[setdiff[1]]" mode="setdiff">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$SETDIFF_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
        <xsl:text>;-</xsl:text>
		<xsl:apply-templates select="*[3]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$SETDIFF_PREC"/>
			<xsl:with-param name="PARAM" select="$PAR_SAME"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="apply[cartesianproduct[1]]">
		<xsl:param name="IN_PREC" select="$NO_PREC"/>
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:choose>
			<xsl:when test="$IN_PREC &gt; $CARTPROD_PREC or $IN_PREC=$CARTPROD_PREC
                    and $PARAM=$PAR_SAME">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
					<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
						<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
					</xsl:if>
					<xsl:apply-templates select="." mode="cartprod">
						<xsl:with-param name="PARAM" select="$PARAM"/>
						<xsl:with-param name="PAREN" select="$PAREN"/>
						<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
					</xsl:apply-templates>
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
			</xsl:when>
			<xsl:when test="$IN_PREC &gt; $NO_PREC and $IN_PREC &lt; $GEN_FUN_PREC
                    and not($SEM_SW=$SEM_ALL) and not($SEM_SW=$SEM_XREF)
                    and not($SEM_SW=$SEM_XREF_EXT)">
				<xsl:apply-templates select="." mode="cartprod">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>

				<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
					<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
				</xsl:if>
				<xsl:apply-templates select="." mode="cartprod">
					<xsl:with-param name="PARAM" select="$PARAM"/>
					<xsl:with-param name="PAREN" select="$PAREN"/>
					<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
				</xsl:apply-templates>

			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="*" mode="cartprod">
		<xsl:param name="PARAM" select="$NO_PARAM"/>
		<xsl:param name="PAREN" select="$PAR_NO"/>
		<xsl:param name="PAR_NO_IGNORE" select="$YES"/>
		<xsl:apply-templates select="*[2]" mode="semantics">
			<xsl:with-param name="IN_PREC" select="$CARTPROD_PREC"/>
			<xsl:with-param name="PARAM" select="$PARAM"/>
			<xsl:with-param name="PAREN" select="$PAREN"/>
			<xsl:with-param name="PAR_NO_IGNORE" select="$PAR_NO_IGNORE"/>
		</xsl:apply-templates>
		<xsl:for-each select="*[position()>2]">

            <xsl:text> 8</xsl:text>

			<xsl:apply-templates select="." mode="semantics">
				<xsl:with-param name="IN_PREC" select="$CARTPROD_PREC"/>
				<xsl:with-param name="PARAM" select="$PAR_SAME"/>
				<xsl:with-param name="PAREN" select="$PAREN"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="apply[card[1]]">
            <xsl:text>_</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:for-each select="*[position()>1]">
				<xsl:apply-templates select="." mode="semantics"/>
			</xsl:for-each>
            <xsl:text>_</xsl:text>
	</xsl:template>
	<!-- ***************** SEQUENCES AND SERIES ***************** -->
	<xsl:template match="apply[sum[1] | product[1]]">
		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>
        <xsl:if test="*[2]=bvar and lowlimit and uplimit">
            <xsl:if test="*[1]=sum">
                <xsl:text>_S</xsl:text>
            </xsl:if>
            <xsl:if test="*[1]=product">
                <xsl:text>_P</xsl:text>
            </xsl:if>

            <xsl:text>.C</xsl:text>
            <xsl:apply-templates select="*[2]" mode="semantics"/>

            <xsl:text> 7</xsl:text>
            <xsl:apply-templates select="lowlimit" mode="semantics"/>

            <xsl:text>./</xsl:text>
            <xsl:apply-templates select="uplimit" mode="semantics"/>
            <xsl:text>:</xsl:text>
            <xsl:apply-templates select="*[5]" mode="semantics"/>
        </xsl:if>
	</xsl:template>
	<xsl:template match="apply[*[1][self::int]]">
            <xsl:text>!</xsl:text>
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="condition">

                    <xsl:text>.*</xsl:text>
				    <xsl:apply-templates select="condition" mode="semantics"/>
                    <xsl:text>:</xsl:text>
				</xsl:when>
				<xsl:when test="lowlimit | uplimit">

                    <xsl:text>.*</xsl:text>
                    <xsl:if test="name(lowlimit/child::node()[1])='cn'">
                        <xsl:value-of select="translate(lowlimit/child::node()[1],'1234567890','1234567890')" />
                    </xsl:if>
                    <xsl:if test="not(name(lowlimit/child::node()[1])='cn')">
                        <xsl:apply-templates select="lowlimit" mode="semantics"/>
                    </xsl:if>

                    <xsl:text>./</xsl:text>
                    <xsl:if test="name(uplimit/child::node()[1])='cn'">
                        <xsl:value-of select="translate(uplimit/child::node()[1],'1234567890','1234567890')" />
                    </xsl:if>
                    <xsl:if test="not(name(uplimit/child::node()[1])='cn')">
                        <xsl:apply-templates select="uplimit" mode="semantics"/>
                        <xsl:text>:</xsl:text>
                    </xsl:if>

				</xsl:when>
				<xsl:otherwise>

				</xsl:otherwise>
			</xsl:choose>
            <xsl:if test="not(*[position()=last() and last()>1 and not(self::domainofapplication) and not(self::condition) and not(self::interval) and not(self::lowlimit) and not(self::uplimit) and not(self::bvar)]=apply[fn|sin|cos|
                       tan | sec | csc |
                       cot | sinh | cosh |
                       tanh | sech | csch |
                       coth | arcsin | arccos |
                       arctan | arcsec | arccsc |
                       arccot | arcsinh | arccosh |
                       arctanh | arcsech | arccsch |
                       arccoth])">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
            </xsl:if>
			<xsl:apply-templates select="*[position()=last() and last()>1 and not(self::domainofapplication) and not(self::condition) and not(self::interval) and not(self::lowlimit) and not(self::uplimit) and not(self::bvar)]" mode="semantics">
				<xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
				<xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
			</xsl:apply-templates>
        <xsl:if test="not(*[position()=last() and last()>1 and not(self::domainofapplication) and not(self::condition) and not(self::interval) and not(self::lowlimit) and not(self::uplimit) and not(self::bvar)]=apply[fn|sin|cos|
                       tan | sec | csc |
                       cot | sinh | cosh |
                       tanh | sech | csch |
                       coth | arcsin | arccos |
                       arctan | arcsec | arccsc |
                       arccot | arcsinh | arccosh |
                       arctanh | arcsech | arccsch |
                       arccoth])">
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
            </xsl:if>
			<xsl:if test="bvar">
                    <xsl:text>;D</xsl:text>
					<xsl:apply-templates select="bvar" mode="semantics"/>
			</xsl:if>

	</xsl:template>
	<xsl:template match="apply[limit[1]]">
		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>

        <xsl:text>$LM</xsl:text>
			<xsl:if test="*[2]=bvar and *[3]=lowlimit">
                <xsl:text>.*</xsl:text>
				<xsl:apply-templates select="*[2]" mode="semantics"/>
                <xsl:text> 3O</xsl:text>
				<xsl:apply-templates select="*[3]" mode="semantics"/>
                <xsl:text>:</xsl:text>
			</xsl:if>
            <xsl:if test="*[2]=bvar and *[3]=condition">
                <xsl:text>.*</xsl:text>
                <xsl:apply-templates select = "*[3]" mode = "semantics"/>
                <xsl:text>:</xsl:text>
            </xsl:if>
		<xsl:apply-templates select="*[4]" mode="semantics"/>
	</xsl:template>
	<!-- ***************** TRIGONOMETRY ***************** -->
	<xsl:template match="apply[*[1][self::sin | self::cos |
                       self::tan | self::sec | self::csc |
                       self::cot | self::sinh | self::cosh |
                       self::tanh | self::sech | self::csch |
                       self::coth | self::arcsin | self::arccos |
                       self::arctan | self::arcsec | self::arccsc |
                       self::arccot | self::arcsinh | self::arccosh |
                       self::arctanh | self::arcsech | self::arccsch |
                       self::arccoth]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:if test="not(parent::apply[power[1]])">
				<xsl:apply-templates select="*[1]" mode="trigonometry"/>
                <xsl:if test="*[2]=apply[plus[1]|minus[1]]">
                    <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
                </xsl:if>
                <xsl:apply-templates select="*[2]" mode="semantics"/>
                <xsl:if test="*[2]=apply[plus[1]|minus[1]]">
                    <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
                </xsl:if>
			</xsl:if>
			<xsl:if test="parent::apply[power[1]]">
                <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
                <xsl:apply-templates select="*[1]" mode="trigonometry"/>
                <xsl:apply-templates select="*[2]" mode="semantics">
                    <xsl:with-param name="IN_PREC" select="$FUNCTN_PREC"/>
                    <xsl:with-param name="PAR_NO_IGNORE" select="$NO"/>
                </xsl:apply-templates>
                <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
                <xsl:text>/</xsl:text>
                <xsl:apply-templates select="../*[3]" mode="semantics"/>
                <xsl:text>:</xsl:text>
			</xsl:if>
	</xsl:template>
	<xsl:template match="sin | cos |
                       tan | sec | csc |
                       cot | sinh | cosh |
                       tanh | sech | csch |
                       coth | arcsin | arccos |
                       arctan | arcsec | arccsc |
                       arccot | arcsinh | arccosh |
                       arctanh | arcsech | arccsch |
                       arccoth">
		<xsl:apply-templates select="." mode="trigonometry"/>
	</xsl:template>
	<xsl:template match="*" mode="trigonometry">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="self::sin">
                    <xsl:text>$S</xsl:text>
				</xsl:when>
				<xsl:when test="self::cos">
                    <xsl:text>$C</xsl:text>
				</xsl:when>
				<xsl:when test="self::tan">
                    <xsl:text>$T</xsl:text>
				</xsl:when>
				<xsl:when test="self::sec">
                    <xsl:text>$SC</xsl:text>
				</xsl:when>
				<xsl:when test="self::csc">
                    <xsl:text>$CS</xsl:text>
				</xsl:when>
				<xsl:when test="self::cot">
                    <xsl:text>$CT</xsl:text>
				</xsl:when>
				<xsl:when test="self::sinh">
                    <xsl:text>$SH</xsl:text>
				</xsl:when>
				<xsl:when test="self::cosh">
                    <xsl:text>$CH</xsl:text>
				</xsl:when>
				<xsl:when test="self::tanh">
                    <xsl:text>$TH</xsl:text>
				</xsl:when>
				<xsl:when test="self::sech">
                    <xsl:text>$SCH</xsl:text>
				</xsl:when>
				<xsl:when test="self::csch">
                    <xsl:text>$CSH</xsl:text>
				</xsl:when>
				<xsl:when test="self::coth">
                    <xsl:text>$CTH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arcsin">
                    <xsl:text>$AS</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccos">
                    <xsl:text>$AC</xsl:text>
				</xsl:when>
				<xsl:when test="self::arctan">
                    <xsl:text>$AT</xsl:text>
				</xsl:when>
				<xsl:when test="self::arcsec">
                    <xsl:text>$ASC</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccsc">
                    <xsl:text>$ACS</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccot">
                    <xsl:text>$ACT</xsl:text>
				</xsl:when>
				<xsl:when test="self::arcsinh">
                    <xsl:text>$ASH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccosh">
                    <xsl:text>$ACH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arctanh">
                    <xsl:text>$ATH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arcsech">
                    <xsl:text>$ASCH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccsch">
                    <xsl:text>$ACSH</xsl:text>
				</xsl:when>
				<xsl:when test="self::arccoth">
                    <xsl:text>$ACTH</xsl:text>
				</xsl:when>
			</xsl:choose>

	</xsl:template>
	<!-- ***************** LINEAR ALGEBRA ***************** -->
	<xsl:template match="vector">
		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>
        <xsl:text disable-output-escaping="yes">&lt;</xsl:text>
		<xsl:for-each select="*">
           <xsl:apply-templates select="." mode="semantics"/>
            <xsl:if test="not(position()=last())">
                <xsl:text> </xsl:text>
            </xsl:if>
		</xsl:for-each>
        <xsl:text disable-output-escaping="yes">&gt;</xsl:text>
	</xsl:template>
	<xsl:template match="matrix">
			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates mode="semantics"/>
	</xsl:template>
	<xsl:template match="matrixrow">
		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>
       <xsl:text>(</xsl:text>
		<xsl:for-each select="*">
			<xsl:apply-templates select="." mode="semantics"/>
            <xsl:if test="not(position()=last())">
                <xsl:text> </xsl:text>
            </xsl:if>
		</xsl:for-each>
        <xsl:text>)</xsl:text>
        <xsl:text>&#xa;</xsl:text>
	</xsl:template>
	<xsl:template match="apply[determinant[1]]">
		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>
        <xsl:text>$DT</xsl:text>

		<xsl:apply-templates select="*[2]" mode="semantics"/>
	</xsl:template>
	<xsl:template match="apply[transpose[1]]">

		<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
			<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
		</xsl:if>
		<xsl:apply-templates select="*[2]" mode="semantics"/>
        <xsl:text>/,T:</xsl:text>
	</xsl:template>

	<xsl:template match="apply[vectorproduct[1] | scalarproduct[1] | outerproduct[1]]">

			<xsl:if test="($SEM_SW=$SEM_XREF or $SEM_SW=$SEM_XREF_EXT) and @id">
				<xsl:attribute name="xref"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<xsl:apply-templates select="*[2]" mode="semantics"/>

				<xsl:if test="vectorproduct[1]">
                    <xsl:text> 8</xsl:text>
				</xsl:if>
				<xsl:if test="scalarproduct[1]">
                    <xsl:text>'</xsl:text>
				</xsl:if>
				<xsl:if test="outerproduct[1]">
                    <xsl:text>;8</xsl:text>
				</xsl:if>

			<xsl:apply-templates select="*[3]" mode="semantics"/>

	</xsl:template>
	<!-- ***************** CONSTANT and SYMBOL ELEMENTS ***************** -->
	<xsl:template match="integers">
        <xsl:text>_Z</xsl:text>
	</xsl:template>
	<xsl:template match="reals">
        <xsl:text>,R</xsl:text>
	</xsl:template>
	<xsl:template match="rationals">
        <xsl:text>,Q</xsl:text>
	</xsl:template>
	<xsl:template match="naturalnumbers">
        <xsl:text>_N</xsl:text>
	</xsl:template>
	<xsl:template match="complexes">
        <xsl:text>,C</xsl:text>
	</xsl:template>
	<xsl:template match="primes">
        <xsl:text>_R</xsl:text>
	</xsl:template>
	<xsl:template match="exponentiale">
        <xsl:text>;E</xsl:text>
	</xsl:template>
	<xsl:template match="imaginaryi">
        <xsl:text>;I</xsl:text>
	</xsl:template>
	<xsl:template match="notanumber">
		<mo> NaN </mo>
	</xsl:template>
	<xsl:template match="true">
		<mo> true </mo>
	</xsl:template>
	<xsl:template match="false">
		<mo> false </mo>
	</xsl:template>
	<xsl:template match="emptyset">
        <xsl:text>@0</xsl:text>
	</xsl:template>
	<xsl:template match="pi">
        <xsl:text>.P</xsl:text>
	</xsl:template>
	<xsl:template match="eulergamma">
        <xsl:text>.G</xsl:text>
	</xsl:template>
	<xsl:template match="infinity">
        <xsl:text>#=</xsl:text>
	</xsl:template>
</xsl:stylesheet>
