package br.com.uol.pagseguro.smartcoffee.printer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import br.com.uol.pagseguro.smartcoffee.MainActivity;
import br.com.uol.pagseguro.smartcoffee.R;
import br.com.uol.pagseguro.smartcoffee.injection.DaggerPrinterComponent;
import br.com.uol.pagseguro.smartcoffee.injection.PrinterComponent;
import br.com.uol.pagseguro.smartcoffee.injection.UseCaseModule;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrinterFragment extends MvpFragment<PrinterContract, PrinterPresenter> implements PrinterContract {

    PrinterComponent mInjector;

    public static PrinterFragment getInstance() {
        return new PrinterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInjector = DaggerPrinterComponent
                .builder()
                .mainComponent(((MainActivity) getContext()).getMainComponent())
                .useCaseModule(new UseCaseModule())
                .build();
        mInjector.inject(this);
        View rootview = inflater.inflate(R.layout.fragment_printer, container, false);
        ButterKnife.bind(this, rootview);
        return rootview;
    }

    @Override
    public PrinterPresenter createPresenter() {
        return mInjector.presenter();
    }

    @OnClick(R.id.btn_nfc_read)
    public void onPrintFileClicked() {
        getPresenter().printFile();
    }

    @Override
    public void showSucess() {
        Snackbar.make(getView(), R.string.printer_print_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Snackbar.make(getView(), R.string.printer_print_failure, Snackbar.LENGTH_LONG).show();
    }
}
