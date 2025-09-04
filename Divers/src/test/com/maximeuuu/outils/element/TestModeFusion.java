package com.maximeuuu.outils.element;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

public class TestModeFusion
{
	private static final Color NOIR = new Color(0,0,0);
	private static final Color NOIR_VIDE = new Color(0,0,0,0);
	private static final Color BLANC = new Color(255,255,255);
	private static final Color BLANC_VIDE = new Color(255,255,0);
	private static final Color ORANGE_SEMI_TRANSPARENT = new Color(255,165,0,127);
	private static final Color BLEU_CANARD = new Color(0,128,128);

	private Color[] ensCouleur;

	@Before
	public void setUp() throws Exception
	{
		this.ensCouleur = new Color[6];
		this.ensCouleur[0] = NOIR;
		this.ensCouleur[1] = NOIR_VIDE;
		this.ensCouleur[2] = BLANC;
		this.ensCouleur[3] = BLANC_VIDE;
		this.ensCouleur[4] = ORANGE_SEMI_TRANSPARENT;
		this.ensCouleur[5] = BLEU_CANARD;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testFusionNormale()
	{

		ModeFusion.NORMAL.fusion( new Color(255,255,255), new Color(0,0,0) );
	}

	@Test
	public void testFusionAdditionSpecifique()
	{
		
	}
}
