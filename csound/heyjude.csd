<CsoundSynthesizer>
<CsOptions>
</CsOptions>
<CsInstruments>
sr = 44100
ksmps = 32
nchnls = 2
0dbfs = 1

gihandle OSCinit 58100
gk1 init 0
gk2 init 0
gk3 init 0
gk4 init 0
gisine ftgen 0, 0, 16384, 10, 1

instr werte
kans OSClisten gihandle, "/wert1", "f", gk1
kans OSClisten gihandle, "/wert2", "f", gk2
kans OSClisten gihandle, "/wert3", "f", gk3
kans OSClisten gihandle, "/wert4", "f", gk4
;gk1 invalue "wert1"
;gk2 invalue "wert2"
;gk3 invalue "wert3"
;gk4 invalue "wert4"
;gk1 port gk1,0.05
;gk2 port gk2,0.05
;gk3 port gk3,0.05
;gk4 port gk4,0.05
outvalue "disp1",gk1
outvalue "disp2",gk2
outvalue "disp3",gk3
outvalue "disp4",gk4
endin

instr Pointer
;gifn	ftgen	0,0, 257, 9, .5,1,270
;a1,a2 diskin2 "heyjude.wav", 1,0,1
a1 soundin "h1.wav"
;a1b,a2b diskin2 "heyjude2.wav", 1,0,1
a2 soundin "h2.wav"
;adist distort1 a1, 2, .5,0,0
if gk1 < 0.5 then
kpoint = 1
else
kpoint = 0
endif
kpoint port kpoint, 0.03
ar ntrpol a1, a2, kpoint
;afilt butbp a1, 400, 10
;abal balance afilt, a1
;antr ntrpol a1, adist, gk3
outs ar,ar
endin

</CsInstruments>
<CsScore>
i 1 0 3600
i 2 0 3600
</CsScore>
</CsoundSynthesizer>
<bsbPanel>
 <label>Widgets</label>
 <objectName/>
 <x>0</x>
 <y>0</y>
 <width>400</width>
 <height>363</height>
 <visible>true</visible>
 <uuid/>
 <bgcolor mode="nobackground">
  <r>255</r>
  <g>255</g>
  <b>255</b>
 </bgcolor>
 <bsbObject version="2" type="BSBController">
  <objectName>disp1</objectName>
  <x>60</x>
  <y>47</y>
  <width>29</width>
  <height>179</height>
  <uuid>{cc889849-49fb-4d33-bebe-a5293c6eebe7}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <objectName2>disp1</objectName2>
  <xMin>0.00000000</xMin>
  <xMax>1.00000000</xMax>
  <yMin>0.00000000</yMin>
  <yMax>1.00000000</yMax>
  <xValue>0.78693682</xValue>
  <yValue>0.78693682</yValue>
  <type>fill</type>
  <pointsize>1</pointsize>
  <fadeSpeed>0.00000000</fadeSpeed>
  <mouseControl act="press">jump</mouseControl>
  <color>
   <r>0</r>
   <g>234</g>
   <b>0</b>
  </color>
  <randomizable mode="both" group="0">false</randomizable>
  <bgcolor>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </bgcolor>
 </bsbObject>
 <bsbObject version="2" type="BSBLabel">
  <objectName/>
  <x>41</x>
  <y>14</y>
  <width>67</width>
  <height>31</height>
  <uuid>{ee1e220a-e6c5-4e59-8de8-4b661c76ddac}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>Wert 1</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>noborder</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBDisplay">
  <objectName>disp1</objectName>
  <x>39</x>
  <y>229</y>
  <width>75</width>
  <height>40</height>
  <uuid>{b89eecb6-774c-4e6c-9ac7-602f09109a1d}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>0.787</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>border</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBController">
  <objectName>disp2</objectName>
  <x>147</x>
  <y>47</y>
  <width>29</width>
  <height>179</height>
  <uuid>{b78fab4b-6dd5-4f2d-b5c0-8fccbde74efe}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <objectName2>disp2</objectName2>
  <xMin>0.00000000</xMin>
  <xMax>1.00000000</xMax>
  <yMin>0.00000000</yMin>
  <yMax>1.00000000</yMax>
  <xValue>0.33390409</xValue>
  <yValue>0.33390409</yValue>
  <type>fill</type>
  <pointsize>1</pointsize>
  <fadeSpeed>0.00000000</fadeSpeed>
  <mouseControl act="press">jump</mouseControl>
  <color>
   <r>0</r>
   <g>234</g>
   <b>0</b>
  </color>
  <randomizable mode="both" group="0">false</randomizable>
  <bgcolor>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </bgcolor>
 </bsbObject>
 <bsbObject version="2" type="BSBLabel">
  <objectName/>
  <x>128</x>
  <y>14</y>
  <width>67</width>
  <height>31</height>
  <uuid>{d2dfccf8-00bf-4d01-abd6-0e4cc96900df}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>Wert 2</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>noborder</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBDisplay">
  <objectName>disp2</objectName>
  <x>126</x>
  <y>229</y>
  <width>75</width>
  <height>40</height>
  <uuid>{823f6339-0a61-4806-87db-e568e3b138b2}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>0.334</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>border</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBController">
  <objectName>disp3</objectName>
  <x>234</x>
  <y>48</y>
  <width>29</width>
  <height>179</height>
  <uuid>{bf9667d1-7a2a-421e-a8cb-663f5fff70ce}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <objectName2>disp3</objectName2>
  <xMin>0.00000000</xMin>
  <xMax>1.00000000</xMax>
  <yMin>0.00000000</yMin>
  <yMax>1.00000000</yMax>
  <xValue>0.55732423</xValue>
  <yValue>0.55732423</yValue>
  <type>fill</type>
  <pointsize>1</pointsize>
  <fadeSpeed>0.00000000</fadeSpeed>
  <mouseControl act="press">jump</mouseControl>
  <color>
   <r>0</r>
   <g>234</g>
   <b>0</b>
  </color>
  <randomizable mode="both" group="0">false</randomizable>
  <bgcolor>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </bgcolor>
 </bsbObject>
 <bsbObject version="2" type="BSBLabel">
  <objectName/>
  <x>215</x>
  <y>14</y>
  <width>67</width>
  <height>31</height>
  <uuid>{331abdac-55f2-4110-b8f4-e3628d57ed62}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>Wert 3</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>noborder</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBDisplay">
  <objectName>disp3</objectName>
  <x>213</x>
  <y>230</y>
  <width>75</width>
  <height>40</height>
  <uuid>{880c9434-4bf5-4e13-b59b-a25fa9bcc386}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>0.557</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>border</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBController">
  <objectName>disp4</objectName>
  <x>320</x>
  <y>47</y>
  <width>29</width>
  <height>179</height>
  <uuid>{bdaedf22-6403-4946-afad-3497408d6ff0}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <objectName2>disp4</objectName2>
  <xMin>0.00000000</xMin>
  <xMax>1.00000000</xMax>
  <yMin>0.00000000</yMin>
  <yMax>1.00000000</yMax>
  <xValue>0.55353570</xValue>
  <yValue>0.55353570</yValue>
  <type>fill</type>
  <pointsize>1</pointsize>
  <fadeSpeed>0.00000000</fadeSpeed>
  <mouseControl act="press">jump</mouseControl>
  <color>
   <r>0</r>
   <g>234</g>
   <b>0</b>
  </color>
  <randomizable mode="both" group="0">false</randomizable>
  <bgcolor>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </bgcolor>
 </bsbObject>
 <bsbObject version="2" type="BSBLabel">
  <objectName/>
  <x>301</x>
  <y>15</y>
  <width>67</width>
  <height>31</height>
  <uuid>{13a9f7f3-fcb8-4f67-b708-9100aa7bc363}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>Wert 4</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>noborder</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBDisplay">
  <objectName>disp4</objectName>
  <x>299</x>
  <y>230</y>
  <width>75</width>
  <height>40</height>
  <uuid>{61659db6-bf21-4106-8e3b-f2d910a496a9}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <label>0.554</label>
  <alignment>left</alignment>
  <font>Arial</font>
  <fontsize>18</fontsize>
  <precision>3</precision>
  <color>
   <r>0</r>
   <g>0</g>
   <b>0</b>
  </color>
  <bgcolor mode="nobackground">
   <r>255</r>
   <g>255</g>
   <b>255</b>
  </bgcolor>
  <bordermode>border</bordermode>
  <borderradius>1</borderradius>
  <borderwidth>1</borderwidth>
 </bsbObject>
 <bsbObject version="2" type="BSBVSlider">
  <objectName>wert1</objectName>
  <x>65</x>
  <y>302</y>
  <width>20</width>
  <height>100</height>
  <uuid>{edba0f4c-9732-46a3-8350-537661a2be08}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <minimum>0.00000000</minimum>
  <maximum>1.00000000</maximum>
  <value>0.64000000</value>
  <mode>lin</mode>
  <mouseControl act="jump">continuous</mouseControl>
  <resolution>-1.00000000</resolution>
  <randomizable group="0">false</randomizable>
 </bsbObject>
 <bsbObject version="2" type="BSBVSlider">
  <objectName>wert2</objectName>
  <x>151</x>
  <y>303</y>
  <width>20</width>
  <height>100</height>
  <uuid>{98fad7e8-b68f-4ae2-ac7f-861845fac97f}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <minimum>0.00000000</minimum>
  <maximum>1.00000000</maximum>
  <value>0.66000000</value>
  <mode>lin</mode>
  <mouseControl act="jump">continuous</mouseControl>
  <resolution>-1.00000000</resolution>
  <randomizable group="0">false</randomizable>
 </bsbObject>
 <bsbObject version="2" type="BSBVSlider">
  <objectName>wert3</objectName>
  <x>238</x>
  <y>303</y>
  <width>20</width>
  <height>100</height>
  <uuid>{d612f7dd-eab5-45d8-87c7-2c67e575fe5e}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <minimum>0.00000000</minimum>
  <maximum>1.00000000</maximum>
  <value>0.00000000</value>
  <mode>lin</mode>
  <mouseControl act="jump">continuous</mouseControl>
  <resolution>-1.00000000</resolution>
  <randomizable group="0">false</randomizable>
 </bsbObject>
 <bsbObject version="2" type="BSBVSlider">
  <objectName>wert4</objectName>
  <x>324</x>
  <y>304</y>
  <width>20</width>
  <height>100</height>
  <uuid>{e48478e4-18f8-40ac-b316-440a93e8501a}</uuid>
  <visible>true</visible>
  <midichan>0</midichan>
  <midicc>0</midicc>
  <minimum>0.00000000</minimum>
  <maximum>1.00000000</maximum>
  <value>1.00000000</value>
  <mode>lin</mode>
  <mouseControl act="jump">continuous</mouseControl>
  <resolution>-1.00000000</resolution>
  <randomizable group="0">false</randomizable>
 </bsbObject>
</bsbPanel>
<bsbPresets>
</bsbPresets>
<MacGUI>
ioView nobackground {65535, 65535, 65535}
ioMeter {60, 47} {29, 179} {0, 59904, 0} "disp1" 0.786937 "disp1" 0.786937 fill 1 0 mouse
ioText {41, 14} {67, 31} label 0.000000 0.00100 "" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder Wert 1
ioText {39, 229} {75, 40} display 0.786937 0.00100 "disp1" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder 0.787
ioMeter {147, 47} {29, 179} {0, 59904, 0} "disp2" 0.333904 "disp2" 0.333904 fill 1 0 mouse
ioText {128, 14} {67, 31} label 0.000000 0.00100 "" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder Wert 2
ioText {126, 229} {75, 40} display 0.333904 0.00100 "disp2" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder 0.334
ioMeter {234, 48} {29, 179} {0, 59904, 0} "disp3" 0.557324 "disp3" 0.557324 fill 1 0 mouse
ioText {215, 14} {67, 31} label 0.000000 0.00100 "" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder Wert 3
ioText {213, 230} {75, 40} display 0.557324 0.00100 "disp3" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder 0.557
ioMeter {320, 47} {29, 179} {0, 59904, 0} "disp4" 0.553536 "disp4" 0.553536 fill 1 0 mouse
ioText {301, 15} {67, 31} label 0.000000 0.00100 "" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder Wert 4
ioText {299, 230} {75, 40} display 0.553536 0.00100 "disp4" left "Arial" 18 {0, 0, 0} {61440, 61440, 61440} nobackground noborder 0.554
ioSlider {65, 302} {20, 100} 0.000000 1.000000 0.640000 wert1
ioSlider {151, 303} {20, 100} 0.000000 1.000000 0.660000 wert2
ioSlider {238, 303} {20, 100} 0.000000 1.000000 0.000000 wert3
ioSlider {324, 304} {20, 100} 0.000000 1.000000 1.000000 wert4
</MacGUI>
