ROBOT       = wolfo01
SHIPDIR     = drivers/${ROBOT}/2
SHIP        = default.xml car5-trb1.rgb

src-robots-wolfo01_PKGFILES = $(shell find * -maxdepth 0 -type f -print)
src-robots-wolfo01_PKGDIR   = ${PACKAGE}-${VERSION}/$(subst ${TORCS_BASE},,$(shell pwd))
include ${MAKE_DEFAULT}