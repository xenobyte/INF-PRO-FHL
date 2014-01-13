##############################################################################
#
#    file                 : Makefile
#    created              : So 30. Mai 17:53:52 CEST 2010
#    copyright            : (C) 2002 Wolfgang Fohl
#
##############################################################################

ROBOT       = wolfo01
MODULE      = ${ROBOT}.so
MODULEDIR   = drivers/${ROBOT}
SOURCES     = ${ROBOT}.cpp driver.cpp opponent.cpp spline.cpp pit.cpp OscServer.cpp

SHIPDIR     = drivers/${ROBOT}
SHIP        = ${ROBOT}.xml cg-nascar-rwd.rgb logo.rgb car5-trb1.rgb
SHIPSUBDIRS = 0 1 2

PKGSUBDIRS  = ${SHIPSUBDIRS}
src-robots-wolfo01_PKGFILES = $(shell find * -maxdepth 0 -type f -print)
src-robots-wolfo01_PKGDIR   = ${PACKAGE}-${VERSION}/$(subst ${TORCS_BASE},,$(shell pwd))

include ${MAKE_DEFAULT}
