package com.nazmul.covid19.covid19graph.ui.graphcontent;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.nazmul.covid19.covid19graph.domain.Day;

public interface IDayDataPresenter  extends IGraphDataPresenter {
	<R> List<R> getDates(Function<Day, R> f, int numOfDays);
}
