<?xml version="1.0" encoding="UTF-8"?><xsl:stylesheet version="1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<xsl:output method="html"/>
		<h1>Website</h1>
		<h2>Pages</h2>
		<ol><xsl:apply-templates select="website/page"/></ol>
		<h2>Widgets</h2>
		<table border = "solid"><thead>
			<tr><th>
					id
				</th>
				<th>
					type
				</th>
				<th>
					text
				</th>
				<th>
					src
				</th>
				<th>
					url
				</th>
				<th>
					label
				</th></tr></thead><tbody>
		<xsl:apply-templates select="website/page/widget"/></tbody></table>	
	</xsl:template>
	
	<xsl:template match="page">
	<li><xsl:value-of select="@name"/>, <xsl:value-of select="description"/></li>
	</xsl:template>
	
	<xsl:template match="page/widget">
		<tr><td><xsl:value-of select="@id" /></td>
			<td><xsl:value-of select="@type" /></td>
			<td><xsl:value-of select="text" /></td>
			<td><xsl:value-of select="@src" /></td>
			<td><xsl:value-of select="@url" /></td>
			<td><xsl:value-of select="@label" /></td></tr></xsl:template>
</xsl:stylesheet>