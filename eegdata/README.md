## EEG-Data


Data samples of raw eeg data.

### File format

File format is comma seperated text as generated by the eeg logger application.

#### Version 0.1

V0.1 filenames end on *.txt and contain a creation timestamp - they are named:

> nameOfAssociatedAction_YYMMDD_HHMMSS.txt

The data columns in V0.1 files are:

> COUNTER,AF3,F7,F3, FC5, T7, P7, O1, O2,P8, T8, FC6, F4,F8, AF4,GYROX, GYROY, TIMESTAMP, FUNC_ID, FUNC_VALUE, MARKER, SYNC_SIGNAL,


#### Version 0.2

V0.2 filenames end on *.eeg - they are named:

> nameOfAssociatedAction_YYMMDD_HHMMSS.eeg

The data columns in V0.2 files are:

> COUNTER,AF3,F7,F3, FC5, T7, P7, O1, O2,P8, T8, FC6, F4,F8, AF4,GYROX, GYROY, TIMESTAMP, FUNC_ID, FUNC_VALUE, MARKER, SYNC_SIGNAL, Cognitiv Action, Cognitiv Power,

### Folder structure

Folder structure is:
/nameOfTestPerson/