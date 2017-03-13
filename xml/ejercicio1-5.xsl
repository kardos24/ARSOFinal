<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" indent="yes" encoding="utf-8" />

	<xsl:template match="/">
		<?xml version="1.0" encoding="utf-8"?>
		<feed xmlns="http://www.w3.org/2005/Atom">
			<title>TVE a la carta</title>
			<subtitle>Programacion disponible de la web de Televisión Española
			</subtitle>
			<link href="" rel="self" />
			<link href="" />
			<updated></updated>
			<author>
				<name>Daniel y Luis</name>
			</author>
			<id>Unique Id</id>

			<xsl:apply-templates />
		</feed>
	</xsl:template>

	<xsl:template match="programa">
		<entry>
			<xsl:apply-templates select="nombre" />
			<xsl:apply-templates select="url-programa" />
			<id>
				<xsl:value-of select="@identificador" />
			</id>
			<updated></updated>
			<summary>Job description</summary>
			<author>
				<name>TVE</name>
			</author>
		</entry>
	</xsl:template>

	<xsl:template match="nombre">
		<title>
			<xsl:value-of select="." />
		</title>
	</xsl:template>

	<xsl:template match="url-programa">
		<link href="{.}" />
	</xsl:template>
</xsl:stylesheet>