package com.subirtupagina.nissandaosa.application;

import android.app.Application;
import android.widget.ArrayAdapter;

import com.subirtupagina.nissandaosa.modelo.Publicacion;
import com.subirtupagina.nissandaosa.utils.AsyncConector;

public class AplicacionNissanDaosa extends Application {
	//Url del archivo XML
	private final static String URL = "http://promoandroid.com/autonova.xml";

	public void updatePublicaciones(ArrayAdapter<Publicacion> lvAdapter) {

		AsyncConector conector = new AsyncConector(lvAdapter, URL);
		conector.execute();
	}

}
