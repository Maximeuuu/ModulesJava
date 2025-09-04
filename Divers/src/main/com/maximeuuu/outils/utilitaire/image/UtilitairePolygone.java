package com.maximeuuu.outils.utilitaire.image;

import java.awt.Polygon;

//TODO: améliorer la lisibilité du code

/**
 * Outils pour les polygones
 * @author Maximeuuu
 */
public class UtilitairePolygone
{
	private UtilitairePolygone(){}

	/**
	 * Simplifie un polygone
	 * @autor : ChatGPT
	 */
	public static Polygon simplifierPolygone( Polygon figure, double tolerance )
	{
		int numPoints = figure.npoints;
		int[] xPoints = figure.xpoints;
		int[] yPoints = figure.ypoints;

		// Créez un tableau pour marquer les points à conserver
		boolean[] keep = new boolean[numPoints];

		// Marquez le premier et le dernier point comme devant être conservés
		keep[0] = true;
		keep[numPoints - 1] = true;

		// Appelez la fonction de simplification récursive
		simplify(xPoints, yPoints, keep, 0, numPoints - 1, tolerance);

		// Comptez le nombre de points à conserver
		int newNumPoints = 0;
		for (int i = 0; i < numPoints; i++) {
			if (keep[i]) {
				newNumPoints++;
			}
		}

		// Créez de nouveaux tableaux pour les coordonnées simplifiées
		int[] newXPoints = new int[newNumPoints];
		int[] newYPoints = new int[newNumPoints];

		// Remplissez les nouveaux tableaux avec les points à conserver
		int newIndex = 0;
		for (int i = 0; i < numPoints; i++) {
			if (keep[i]) {
				newXPoints[newIndex] = xPoints[i];
				newYPoints[newIndex] = yPoints[i];
				newIndex++;
			}
		}

		figure = new Polygon(newXPoints, newYPoints, newNumPoints);

		return figure;
	}

	/**
	 * Methode utilisee pour simplifier un polygone
	 * @autor : ChatGPT
	 */
	private static void simplify(int[] x, int[] y, boolean[] keep, int start, int end, double tolerance) {
		if (start + 1 >= end) {
			// Il ne reste que deux points, ne les simplifiez pas davantage
			return;
		}

		// Trouvez l'indice du point le plus éloigné de la ligne entre les points de début et de fin
		int farthestIdx = start;
		double maxDistance = 0.0;

		for (int i = start + 1; i < end; i++) {
			double distance = distanceToLine(x[i], y[i], x[start], y[start], x[end], y[end]);

			if (distance > maxDistance) {
				maxDistance = distance;
				farthestIdx = i;
			}
		}

		if (maxDistance > tolerance) {
			// Marquez le point le plus éloigné comme devant être conservé
			keep[farthestIdx] = true;

			// Réalisez la simplification récursivement sur les deux segments
			simplify(x, y, keep, start, farthestIdx, tolerance);
			simplify(x, y, keep, farthestIdx, end, tolerance);
		}
	}

	/**
	 * Methode utilisee pour simplifier un polygone
	 * @autor : ChatGPT
	 */
	private static double distanceToLine(int x, int y, int x1, int y1, int x2, int y2) {
		double a = x - x1;
		double b = y - y1;
		double c = x2 - x1;
		double d = y2 - y1;

		double dot = a * c + b * d;
		double lenSq = c * c + d * d;
		double param = (lenSq != 0) ? dot / lenSq : -1;

		double xx, yy;

		if (param < 0) {
			xx = x1;
			yy = y1;
		} else if (param > 1) {
			xx = x2;
			yy = y2;
		} else {
			xx = x1 + param * c;
			yy = y1 + param * d;
		}

		double dx = x - xx;
		double dy = y - yy;

		return Math.sqrt(dx * dx + dy * dy);
	}
}