package Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {
	static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	static Locale locale = new Locale("en", "IN");
	static DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
	static DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);

	public static String getFormatted(double amt) {
		dfs.setCurrencySymbol("");
		decimalFormat.setDecimalFormatSymbols(dfs);
		return decimalFormat.format(amt);

	}
}