#language: es
#encoding: UTF-8
#author: Alan Eduardo Diaz Arosemena

Característica: Descarga y validacion de certificado FACTA & CRS en Banistmo
  Como usuario de la pagina de Banistmo
  Quiero navegar hasta la sección de legales y FACTA & CRS
  Para descargar y validar el documento de autocertificacion fiscal

  @descargarDocumentoInformativo
  Escenario: Descarga Exitosa del documento PDF de autocertificacion fiscal FACTA & CRS
    Dado que el usuario navega a la pagina principal de Banistmo Personas
    Cuando navega a la seccion de aprender es facil desde el pie de pagina
    Y selecciona la opcion de legales y accede a FACTA & CRS
    Y descarga el documento  PDF de Autocertificacion unificada FACTA y CRS
    Entonces el usuario valida que el documento generado sea el correcto