#include "stdafx.h"
#include "EpocData.h"
#include <stdlib.h>
#include <stdexcept>
class EpocData{
	int samplesTaken;
	double engagement, frustration, meditation, excitement;
	int	*COUNTER, *GYROX, *GYROY, *TIMESTAMP,
		*FUNC_ID, *FUNC_VALUE, *MARKER, *SYNC_SIGNAL;
	double	*AF3, *F7, *F3, *FC5, *T7,
		*P7, *O1, *O2, *P8, *T8,
		*FC6, *F4, *F8, *AF4;

	EpocData::EpocData(int pSamplesTaken, double pEngagement, double pFrustration, double pMeditation, double pExcitement, 
		int *CounterAry, int *GyroXAry, int *GyroYAry, int *TimestampAry, 
		int *Func_IdAry, int *Func_ValueAry, int *markerAry, int *Sync_SignalAry,
		double *AF3Ary, double *F7Ary, double *F3Ary, double *FC5Ary,double *T7Ary,
		double *P7Ary,double *O1Ary,double *O2Ary,double *P8Ary,double *T8Ary,
		double *FC6Ary,double *F4Ary,double *F8Ary,double *AF4Ary)
	{
		samplesTaken = pSamplesTaken;
		engagement = pEngagement;
		frustration = pFrustration;
		meditation= pMeditation;
		excitement = pExcitement;

		COUNTER = (int*) malloc(samplesTaken);
		memcpy(COUNTER, CounterAry, samplesTaken);
		GYROX = (int*) malloc(samplesTaken);
		memcpy(GYROX, GyroXAry, samplesTaken);
		GYROY = (int*) malloc(samplesTaken);
		memcpy(GYROY, GyroYAry, samplesTaken);
		TIMESTAMP = (int*) malloc(samplesTaken);
		memcpy(TIMESTAMP, TimestampAry, samplesTaken);
		FUNC_ID = (int*) malloc(samplesTaken);
		memcpy(FUNC_ID, Func_IdAry, samplesTaken);
		FUNC_VALUE = (int*) malloc(samplesTaken);
		memcpy(FUNC_VALUE, Func_ValueAry, samplesTaken);
		MARKER = (int*) malloc(samplesTaken);
		memcpy(MARKER, markerAry, samplesTaken);
		SYNC_SIGNAL = (int*) malloc(samplesTaken);
		memcpy(SYNC_SIGNAL, Sync_SignalAry, samplesTaken);

		AF3 = (double*) malloc(samplesTaken);
		memcpy(AF3, AF3Ary, samplesTaken);
		F7 = (double*) malloc(samplesTaken);
		memcpy(F7, F7Ary, samplesTaken);
		F3 = (double*) malloc(samplesTaken);
		memcpy(F3, F3Ary, samplesTaken);
		FC5 = (double*) malloc(samplesTaken);
		memcpy(FC5, FC5Ary, samplesTaken);
		T7 = (double*) malloc(samplesTaken);
		memcpy(T7, T7Ary, samplesTaken);
		P7 = (double*) malloc(samplesTaken);
		memcpy(P7, P7Ary, samplesTaken);
		O1 = (double*) malloc(samplesTaken);
		memcpy(O1, O1Ary, samplesTaken);
		O2 = (double*) malloc(samplesTaken);
		memcpy(O2, O2Ary, samplesTaken);
		P8 = (double*) malloc(samplesTaken);
		memcpy(P8, P8Ary, samplesTaken);
		T8 = (double*) malloc(samplesTaken);
		memcpy(T8, T8Ary, samplesTaken);
		FC6 = (double*) malloc(samplesTaken);
		memcpy(FC6, FC6Ary, samplesTaken);
		F4 = (double*) malloc(samplesTaken);
		memcpy(F4, F4Ary, samplesTaken);
		F8 = (double*) malloc(samplesTaken);
		memcpy(F8, F8Ary, samplesTaken);
		AF4 = (double*) malloc(samplesTaken);
		memcpy(AF4, AF4Ary, samplesTaken);
	}

	EpocData::~EpocData()
	{
		free(COUNTER);
		free(GYROX);
		free(GYROY);
		free(TIMESTAMP);
		free(FUNC_ID);
		free(FUNC_VALUE);
		free(MARKER);
		free(SYNC_SIGNAL);

		free(AF3);
		free(F7);
		free(F3);
		free(FC5);
		free(T7);
		free(P7);
		free(O1);
		free(O2);
		free(P8);
		free(F8);
		free(FC6);
		free(F4);
		free(AF4);
	}

	double getSamplesTaken(){
		return samplesTaken;
	}

	double getEngagement(){
		return engagement;
	}

	double getFrustation(){
		return frustration;
	}

	double getMeditation(){
		return meditation;
	}

	double getExcitment(){
		return excitement;
	}


	int getCOUNTER(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return COUNTER[index];
	}

	int getGYROX(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return GYROX[index];
	}

	int getGYROY(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return GYROY[index];
	}

	int getTIMESTAMP(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return TIMESTAMP[index];
	}
	int getFUNC_ID(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return FUNC_ID[index];
	}

	int getFUNC_VALUE(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return FUNC_VALUE[index];
	}

	int getMARKER(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return MARKER[index];
	}

	int getSYNC_SIGNAL(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return SYNC_SIGNAL[index];
	}

	double getAF3(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return AF3[index];
	}

	double getF7(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return F7[index];
	}

	double getF3(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return F3[index];
	}

	double getAF3(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return AF3[index];
	}

	double getFC5(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return FC5[index];
	}

	double getFC5(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return FC5[index];
	}

	double getT7(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return T7[index];
	}

	double getP7(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return P7[index];
	}

	double getO1(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return O1[index];
	}

	double getO2(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return O2[index];
	}

	double getF8(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return F8[index];
	}

	double getP8(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return P8[index];
	}

	double getFC6(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return FC6[index];
	}

	double getF4(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return F4[index];
	}

	double getAF4(int index){
		if (index >= samplesTaken)
			throw std::out_of_range ("Index out of bounds.");
		return AF4[index];
	}

	void setAF3(double *pAF3)
	{
		memcpy(AF3, pAF3, samplesTaken);		
	}

	void setAF4(double *pAF4)
	{
		memcpy(AF4, pAF4, samplesTaken);		
	}

	void setCounter(int *pCounter)
	{
		memcpy(COUNTER, pCounter, samplesTaken);		
	}

	void setExcitement(double pExcitement)
	{
		excitement = pExcitement;
	}

	void setEngagement(double pEngagement)
	{
		engagement =pEngagement;		
	}

	void setF3(double *pF3)
	{
		memcpy(F3, pF3, samplesTaken);		
	}

	void setF4(double *pF4)
	{
		memcpy(AF4, pF4, samplesTaken);		
	}

	void setF7(double *pF7)
	{
		memcpy(F7, pF7, samplesTaken);		
	}

	void setF8(double *pF8)
	{
		memcpy(F8, pF8, samplesTaken);		
	}

	void setFC5(double *pFC5)
	{
		memcpy(FC5, pFC5, samplesTaken);		
	}

	void setFC6(double *pFC6)
	{
		memcpy(FC6, pFC6, samplesTaken);		
	}

	void setFrustration(double pFrustration)
	{
		frustration = pFrustration;
	}

	void setFuncId(int *pFuncId)
	{
		memcpy(FUNC_ID, pFuncId, samplesTaken);		
	}

	void setFuncValue(int *pFuncValue)
	{
		memcpy(FUNC_VALUE, pFuncValue, samplesTaken);		
	}

	void setGyroX(int *pGyroX)
	{
		memcpy(GYROX, pGyroX, samplesTaken);		
	}


	void setGyroY(int *pGyroY)
	{
		memcpy(GYROY, pGyroY, samplesTaken);		
	}


	void setMarker(int *pMarker)
	{
		memcpy(MARKER, pMarker, samplesTaken);		
	}


	void setMeditation(double pMeditation)
	{
		meditation = pMeditation;
	}


	void setO1(double *pO1)
	{
		memcpy(O1, pO1, samplesTaken);		
	}


	void setO2(double *pO2)
	{
		memcpy(O2, pO2, samplesTaken);		
	}

	void setP7(double *pP7)
	{
		memcpy(P7, pP7, samplesTaken);		
	}


	void setP8(double *pP8)
	{
		memcpy(P8, pP8, samplesTaken);		
	}


	void setSyncSignal(int *pAF3)
	{
		memcpy(AF3, pAF3, samplesTaken);		
	}


	void setT7(double *pT7)
	{
		memcpy(T7, pT7, samplesTaken);		
	}


	void setTimestamp(int *pTimestamp)
	{
		memcpy(TIMESTAMP, pTimestamp, samplesTaken);		
	}



};