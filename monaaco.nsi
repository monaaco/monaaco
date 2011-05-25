  !include "MUI.nsh"
  !include MUI2.nsh
;!include fileassoc.nsh
  
  

;Seleccionamos el algoritmo de compresión utilizado para comprimir nuestra aplicación
SetCompressor lzma
;--------------------------------
;Con esta opción alertamos al usuario cuando pulsa el botón cancelar y le pedimos confirmación para abortar
;la instalación
;Esta macro debe colocarse en esta posición del script sino no funcionara
  

;Definimos el valor de la variable VERSION, en caso de no definirse en el script
;podria ser definida en el compilador
!define VERSION "1.0"

 !define MUI_HEADERIMAGE 
 !define MUI_HEADERIMAGE_BITMAP "mono2.bmp" ; optional
 !define MUI_HEADERIMAGE_UNBITMAP "mono2.bmp"
 
 
 !define MUI_ABORTWARNING
 !define MUI_WELCOMEFINISHPAGE_BITMAP "mono.bmp"
 ;!define MUI_WELCOMEFINISHPAGE_BITMAP_NOSTRETCH "mono.bmp"
 !define MUI_UNWELCOMEFINISHPAGE_BITMAP  "mono.bmp"
 ;!define MUI_BGCOLOR "F2F"
 !define MUI_ICON mono.ico
 !define MUI_UNICON mono.ico
 !define MUI_PAGE_HEADER_TEXT "Bienvenido a la instalación de Monaaco"
 ;LICENCIAAAAAAAAAAAAAAAA
 !define MUI_LICENSEPAGE_BGCOLOR "FFFF99"
 !define MUI_DIRECTORYPAGE_TEXT_TOP "Por favor, elija el directorio dónde desea instalar Monaaco"
 
 

;--------------------------------
;Pages

  ;Mostramos la página de bienvenida 
  !insertmacro MUI_PAGE_WELCOME 
  ;Página donde mostramos el contrato de licencia 
  ;!insertmacro MUI_PAGE_LICENSE "licencia.txt" 
  ;!insertmacro MUI_PAGE_LICENSE "licencia.txt;
  !insertmacro MUI_PAGE_LICENSE  "mono.txt"
  ;página donde se muestran las distintas secciones definidas 
  ;!insertmacro MUI_PAGE_COMPONENTS 
  ;página donde se selecciona el directorio donde instalar nuestra aplicacion 
  !insertmacro MUI_PAGE_DIRECTORY 
  
  ;página de instalación de ficheros 
  !insertmacro MUI_PAGE_INSTFILES 
  ;página final
  !insertmacro MUI_PAGE_FINISH
  
  
  ; !insertmacro APP_ASSOCIATE "mp3" "myapp.textfile" "$INSTDIR\mono.exe,0"
 

;páginas referentes al desinstalador
!insertmacro MUI_UNPAGE_WELCOME
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH

;!insertmacro MUI_WELCOMEFINISHPAGE_BITMAP "mono.bmp"



;!insertmacro MUI_DEFAULT UMUI_HEADERIMAGE_BMP \
 ;   "\mono.png"
;--------------------------------
;Languages

!insertmacro MUI_LANGUAGE "Spanish"

; Para generar instaladores en diferentes idiomas podemos escribir lo siguiente:
;  !insertmacro MUI_LANGUAGE ${LANGUAGE}
; De esta forma pasando la variable LANGUAGE al compilador podremos generar
;paquetes en distintos idiomas sin cambiar el script

;;;;;;;;;;;;;;;;;;;;;;;;;
; Configuración General ;
;;;;;;;;;;;;;;;;;;;;;;;;;
;Nuestro instalador se llamara si la versión fuera la 1.0: Ejemplo-1.0-win32.exe
OutFile Monaaco-1.0-win32.exe

;Aquí comprobamos que en la versión Inglesa se muestra correctamente el mensaje:
;Welcome to the $Name Setup Wizard
;Al tener reservado un espacio fijo para este mensaje, y al ser
;la frase en español mas larga:
; Bienvenido al Asistente de Instalación de Aplicación $Name
; no se ve el contenido de la variable $Name si el tamaño es muy grande
Name "Monaaco"
Caption "Mono 1.0 para Win32 Setup"

;Icon mono.ico

;Comprobacion de integridad del fichero activada
CRCCheck on
;Estilos visuales del XP activados
XPStyle on

/*
        Declaracion de variables a usar

*/
# también comprobamos los distintos
; tipos de comentarios que nos permite este lenguaje de script

Var PATH
Var PATH_ACCESO_DIRECTO
Var IMAGES
Var MONO_LIB
Var XML
Var SOUNDS
;Indicamos cual será el directorio por defecto donde instalaremos nuestra
;aplicación, el usuario puede cambiar este valor en tiempo de ejecución.
InstallDir "$PROGRAMFILES\Monaaco"

; check if the program has already been installed, if so, take this dir
; as install dir
InstallDirRegKey HKLM SOFTWARE\MONO "Install_Dir"
;Mensaje que mostraremos para indicarle al usuario que seleccione un directorio
DirText "Elija un directorio donde instalar la aplicación:"
;Indicamos que cuando la instalación se complete no se cierre el instalador automáticamente
AutoCloseWindow false
;Mostramos todos los detalles del la instalación al usuario.
ShowInstDetails show
;En caso de encontrarse los ficheros se sobreescriben
SetOverwrite on
;Optimizamos nuestro paquete en tiempo de compilación, es altamente recomendable habilitar siempre esta opción
SetDatablockOptimize on
;Habilitamos la compresión de nuestro instalador
SetCompress auto
;Personalizamos el mensaje de desinstalación
UninstallText "Este es el desinstalador del Monaaco."


Section "Monaaco"
StrCpy $PATH "Monaaco"
StrCpy $PATH_ACCESO_DIRECTO "Monaaco"
SetOutPath $INSTDIR\$PATH

;StrCpy $MONO_LIB "mono_lib"
;SetOutPath $INSTDIR\$MONO_LIB


;File "commons-logging-api.jar"
;File "jaudiotagger-2.0.1.jar"
;


;StrCpy $SOUNDS "sounds"
;SetOutPath $INSTDIR\$SOUNDS

;StrCpy $XML "xml"
;SetOutPath $INSTDIR\$XML

;StrCpy $IMAGES "images"
;SetOutPath $INSTDIR\$IMAGES

SetOutPath $INSTDIR

;CreateDirectory $INSTDIR\backup
;CopyFiles $INSTDIR\*.dat $INSTDIR\backup
;CopyFiles $INSTDIR\*.jar $INSTDIR\

;Incluimos todos los ficheros que componen nuestra aplicación
File   mono.exe
File   licencia.html
;File   config.ini
File   *.txt
;File *.jar

;Opción /r te coje los archivos de manera recursiva

File /r *.jar
File /r *.png
File /r *.xml
File /r *.mp3

;Hacemos que la instalación se realice para todos los usuarios del sistema
SetShellVarContext all
;Creamos los directorios, acesos directos y claves del registro que queramos...
	CreateDirectory "$SMPROGRAMS\$PATH_ACCESO_DIRECTO"
        CreateShortCut "$SMPROGRAMS\$PATH_ACCESO_DIRECTO\mono.lnk" \
                       "$INSTDIR\mono.exe" "--parametros parametro1"
					   
		CreateShortCut "$DESKTOP\mono.lnk"\
						"$INSTDIR\mono.exe" "--parametros parametro1"
	
        CreateShortCut "$SMPROGRAMS\$PATH_ACCESO_DIRECTO\Licencia.lnk" \
                       "$INSTDIR\licencia.html"

;Creamos también el aceso directo al instalador
        CreateShortCut "$SMPROGRAMS\$PATH_ACCESO_DIRECTO\Desinstalar.lnk" \
                       "$INSTDIR\uninstall.exe"

        WriteRegStr HKLM \
            SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$PATH \
            "DisplayName" "Monaaco ${VERSION}"
        WriteRegStr HKLM \
            SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$PATH \
            "UninstallString" '"$INSTDIR\uninstall.exe"'
        WriteUninstaller "uninstall.exe"

        WriteRegStr HKLM SOFTWARE\$PATH "InstallDir" $INSTDIR
       
        WriteRegStr HKLM SOFTWARE\$PATH "Version" "1.0"
        ;Mostramos el directorio donde se crearon los acesos directos
		
		;WriteRegStr HKCR "mp3" "backup" "$INSTDIR\mono.exe,0"
 
	;	WriteRegStr HKCR ".${EXT}" "" "${FILECLASS}"
	;:	StrCmp "${SHELLNEW}" "0" +2
		;WriteRegStr HKCR ".${EXT}\ShellNew" "NullFile" ""
 
		;WriteRegStr HKCR "${FILECLASS}" "" `${DESCRIPTION}`
		;WriteRegStr HKCR "${FILECLASS}\DefaultIcon" "" `${ICON}`
		;WriteRegStr HKCR "${FILECLASS}\shell" "" `${DEFAULTVERB}`
		;:WriteRegStr HKCR "${FILECLASS}\shell\${VERB}" "" `${COMMANDTEXT}`
		;WriteRegStr HKCR "${FILECLASS}\shell\${VERB}\command" "" `${COMMAND}`
		
		
		
	Exec "explorer $SMPROGRAMS\$PATH_ACCESO_DIRECTO\"
SectionEnd


;Section "Ayuda"
 ;       SetOutPath $INSTDIR\$PATH
  ;      StrCpy $PATH "MONO"
  ;      StrCpy $PATH_ACCESO_DIRECTO "_MONO_"
;Estos directorios han de contener algún fichero, sino el compilador
;dara el error: File: "ayuda" -> no files found.
;En caso de querer que nuestra aplicación se creen directorios vacios una opción
;es crear un fichero dummy.txt
  ;      File /r ayuda
;SectionEnd

Section "Skins"
        SetOutPath $INSTDIR\$PATH
        StrCpy $PATH "Monaaco"
        StrCpy $PATH_ACCESO_DIRECTO "Monaaco"
     ;   File  /r skins
SectionEnd

Section "Plugins"
        SetOutPath $INSTDIR\$PATH
        StrCpy $PATH "Monaaco"
        StrCpy $PATH_ACCESO_DIRECTO "Monaaco"
     ;   File   /r plugins
SectionEnd

Function .onInit
        # the plugins dir is automatically deleted when the installer exits
        InitPluginsDir
        File /oname=$PLUGINSDIR\splash.bmp "\mono.bmp"
        #optional
        #File /oname=$PLUGINSDIR\splash.wav "\mono.wav"

        #MessageBox MB_OK "Fading"

        advsplash::show 1000 600 400 -1 $PLUGINSDIR\splash

        Pop $0          ; $0 has '1' if the user closed the splash screen early,
                        ; '0' if everything closed normally, and '-1' if some error occurred.

  #      MessageBox MB_OK "Transparency"
   #     File /oname=$PLUGINSDIR\splash.bmp "${NSISDIR}\Contrib\Graphics\Wizard\orange-uninstall.bmp"
    #    advsplash::show 2000 0 0 0x1856B1 $PLUGINSDIR\splash
     #   Pop $0 

      #  MessageBox MB_OK "Transparency/Fading"
       # File /oname=$PLUGINSDIR\splash.bmp "${NSISDIR}\Contrib\Graphics\Wizard\llama.bmp"
        
		#advsplash::show 1000 600 400 0x04025C $PLUGINSDIR\splash
        #Pop $0 
		

        Delete $PLUGINSDIR\splash.bmp
FunctionEnd
Section "Uninstall"
        StrCpy $PATH "Monaaco"
        StrCpy $PATH_ACCESO_DIRECTO "Monaaco"
		;CreateShortCut "$DESKTOP\mono.lnk"\
		;				"$INSTDIR\mono.exe" "--parametros parametro1"
		
        SetShellVarContext all
        RMDir /r $SMPROGRAMS\$PATH_ACCESO_DIRECTO
        RMDir /r $INSTDIR\$PATH
        RMDir /r $INSTDIR
		;RMDir $DESKTOP
		Delete  $DESKTOP\mono.lnk
        DeleteRegKey HKLM SOFTWARE\$PATH
        DeleteRegKey HKLM \
            Software\Microsoft\Windows\CurrentVersion\Uninstall\$PATH
SectionEnd






