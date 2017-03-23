<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tns="http://www.example.org/ejercicio1-2">

	<xsl:output method="xml" indent="yes" encoding="utf-8" />

	<xsl:template match="/">
		<feed xmlns="http://www.w3.org/2005/Atom">
			<xsl:apply-templates select="tns:nombre" />
			<subtitle>
				<xsl:apply-templates select="tns:url-portada" />
			</subtitle>
			<link href="" rel="self" />
			<xsl:apply-templates select="tns:url-programa" />
			<updated>
				<xsl:value-of select="tns:emision[1]/fecha" />
			</updated>
			<author>
				<name>Televisión Española</name>
			</author>
			<id>
				<xsl:value-of select="@identificador" />
			</id>

			<xsl:apply-templates select="tns:emision" />
		</feed>
	</xsl:template>

	<xsl:template match="tns:nombre">
		<title>
			<xsl:value-of select="." />
		</title>
	</xsl:template>

	<xsl:template match="tns:url-programa">
		<link href="{.}" />
	</xsl:template>

	<xsl:template match="tns:url-portada">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="tns:emision">
		<entry>
			<title>
				<xsl:apply-templates select="tns:titulo" />
			</title>
			<xsl:apply-templates select="tns:url-emision" />
			<id>
				<xsl:value-of select="../@identificador" />
			</id>
			<updated>
				<xsl:apply-templates select="tns:fecha" />
			</updated>
			<summary>
				Tiempo de emisión:
				<xsl:apply-templates select="tns:tiempo-emision" />
			</summary>
			<author>
				<name>Televisión Española</name>
			</author>
		</entry>
	</xsl:template>

	<xsl:template match="tns:titulo">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="tns:url-emision">
		<link href="{.}" />
	</xsl:template>

	<xsl:template match="tns:fecha">
		<xsl:value-of select="." />
	</xsl:template>

	<xsl:template match="tns:tiempo-emision">
		<xsl:value-of select="." />
	</xsl:template>
</xsl:stylesheet>