package fr.flexilog.steplog;

import fr.flexilog.steplog.format.FormatAssemblage;
import fr.flexilog.steplog.format.FormatBarre;
import fr.flexilog.steplog.format.FormatNumerique;
import fr.flexilog.steplog.format.FormatPourcentage;
import fr.flexilog.steplog.format.IFormat;

public class VisualTestStepByStepLog
{
	private IFormat format;
	private StepByStepLog sbsl;
	private static final int NB_ETAPES = 374;

	public static void main(String[] args)
	{
		new VisualTestStepByStepLog();
	}

	public VisualTestStepByStepLog()
	{
		this.testFormatBarre();
		this.testFormatNumerique();
		this.testFormatPourcentage();
		this.testFormatAssemblage();
	}

	public void testFormatBarre()
	{
		format = new FormatBarre();
		sbsl = new StepByStepLog(NB_ETAPES, 1, format);
		this.testMethodes();

		format = FormatBarre.getBarre(FormatBarre.ASCII);
		sbsl = new StepByStepLog(NB_ETAPES, 30, format);
		this.testMethodes();

		format = FormatBarre.getBarre(FormatBarre.POINT);
		sbsl = new StepByStepLog(NB_ETAPES, 1, format);
		this.testMethodes();
	}

	public void testFormatNumerique()
	{
		format = new FormatNumerique();
		sbsl = new StepByStepLog(NB_ETAPES, 1, format);
		this.testMethodes();
	}

	public void testFormatPourcentage()
	{
		format = new FormatPourcentage();
		sbsl = new StepByStepLog(NB_ETAPES, 1, format);
		this.testMethodes();
	}

	public void testFormatAssemblage()
	{
		format = new FormatAssemblage(new FormatNumerique(), new FormatBarre(), new FormatPourcentage());
		sbsl = new StepByStepLog(NB_ETAPES, 1, format);
		this.testMethodes();
	}

	private void testMethodes( )
	{
		for( int cpt=0; cpt<NB_ETAPES; cpt++)
		{
			this.sbsl.processing("Test chargement");
			try{ Thread.sleep(5); }catch( InterruptedException ie ){}
		}
		this.sbsl.end("Traitement termine");
	}
}
