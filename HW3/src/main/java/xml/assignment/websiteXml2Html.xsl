<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<H1>Website</H1>
		<H2>Pages</H2>
		<ol>
			<xsl:apply-templates select="website/page"/>
		</ol>
		<TABLE border="1px solid black">
			<THEAD>
				<TR>
					<TH>id</TH>
					<TH>type</TH>
					<TH>text</TH>
					<TH>src</TH>
					<TH>url</TH>
					<TH>label</TH>
				</TR>
			</THEAD>
			<TBODY>
				<xsl:apply-templates select="website/page/widget"/>
			</TBODY>
		</TABLE>
			
	</xsl:template>
	
	<xsl:template match="page">
	 	<li><xsl:value-of select="@name"/>, <xsl:value-of select="description"/></li>
	</xsl:template>
	
	<xsl:template match="page/widget">
		<TR>
			<TD>
				<xsl:value-of select="@id" />
			</TD>
			<TD>
				<xsl:value-of select="@type" />
			</TD>
			<TD>
				<xsl:value-of select="text" />
			</TD>
			<TD>
				<xsl:value-of select="@src" />
			</TD>
			<TD>
				<xsl:value-of select="@url" />
			</TD>
			<TD>
				<xsl:value-of select="@label" />
			</TD>
		</TR>
	</xsl:template>
	
</xsl:stylesheet>