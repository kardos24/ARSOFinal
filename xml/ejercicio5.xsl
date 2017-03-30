<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:e="http://www.example.org/ejercicio2">

	<xsl:output method="xml" indent="yes" encoding="utf-8" />

	<xsl:template match="/">
		<feed xmlns="http://www.w3.org/2005/Atom">
			<xsl:apply-templates select="e:programa" />
		</feed>
	</xsl:template>

	<xsl:template match="e:programa">
		<title>
			<xsl:value-of select="e:nombre" />
		</title>
		<link href="" rel="self" />
		<link href="{e:url-programa}" />
		<updated>
			<xsl:value-of select="e:emision[1]/e:fecha" />
		</updated>
		<author>
			<name>Televisión Española</name>
		</author>
		<id>
			<xsl:value-of select="@identificador" />
		</id>
		<logo>
			<xsl:value-of select="e:url-portada" />
		</logo>
		<xsl:apply-templates select="e:emision" />
	</xsl:template>

	<xsl:template match="e:emision">
		<entry>
			<title>
				<xsl:value-of select="e:titulo" />
			</title>
			<link href="{e:url-emision}" />
			<id>
				<xsl:value-of select="../@identificador" />
			</id>
			<updated>
				<xsl:value-of select="e:fecha" />
			</updated>
			<summary>Tiempo de emisión: <xsl:value-of select="e:tiempo-emision" /></summary>
			<author>
				<name>Televisión Española</name>
			</author>
		</entry>
	</xsl:template>
</xsl:stylesheet>