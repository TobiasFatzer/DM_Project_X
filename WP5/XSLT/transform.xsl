<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Dont Speed Kids</title>
            </head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
                  integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
                  crossorigin="anonymous"/>
            <body>
                <h1>This is the Degree Graph Data from Teams and Assessment</h1>
                <a href="index.html">So this goes back then</a>
                <table class="container table table-striped" style="margin-top: 12px;">
                    <thead>
                        <tr>
                            <th>Team Name</th>
                            <th>Degree</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="root/row">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"/>
                                </td>
                                <td>
                                    <xsl:value-of select="degree"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>