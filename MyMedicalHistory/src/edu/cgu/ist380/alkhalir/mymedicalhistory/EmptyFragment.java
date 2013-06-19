package edu.cgu.ist380.alkhalir.mymedicalhistory;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

public class EmptyFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		      Bundle savedInstanceState) {
		return new Space(getActivity());
	}

}
