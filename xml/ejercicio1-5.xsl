<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes" encoding="utf-8" />

	<xsl:template match="/">
		<feed xmlns="http://www.w3.org/2005/Atom">
			<xsl:apply-templates select="nombre" />
			<subtitle>
				<xsl:apply-templates select="url-portada" />
			</subtitle>
			<link href="" rel="self" />
			<xsl:apply-templates select="url-programa" />
			<updated>
				<xsl:value-of select="emision[1]/fecha" />
			</updated>
			<author>
				<name>Televisión Española</name>
			</author>
			<id>
				<xsl:value-of select="@identificador" />
			</id>

			<xsl:apply-templates select="emision" />
		</feed>
	</xsl:template>

	<xsl:template match="nombre">
		<title>
			<xsl:value-of select="." />
		</title>
	</xsl:template>

	<xsl:template match="url-programa">
		<link href="{.}" />
	</xsl:template>

	<xsl:template match="url-portada">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="emision">
		<entry>
			<title>
				<xsl:apply-templates select="titulo" />
			</title>
			<xsl:apply-templates select="url-emision" />
			<id>
				<xsl:value-of select="../@identificador" />
			</id>
			<updated>
				<xsl:apply-templates select="fecha" />
			</updated>
			<summary>
				Tiempo de emisión:
				<xsl:apply-templates select="tiempo-emision" />
			</summary>
			<author>
				<name>Televisión Española</name>
			</author>
		</entry>
	</xsl:template>

	<xsl:template match="titulo">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="url-emision">
		<link href="{.}" />
	</xsl:template>

	<xsl:template match="fecha">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="tiempo-emision">
		<xsl:value-of select="." />
	</xsl:template>
</xsl:stylesheet>