package br.com.uol.pagseguro.smartcoffee.injection;

import br.com.uol.pagseguro.plugpagservice.wrapper.PlugPag;
import br.com.uol.pagseguro.smartcoffee.printer.PrinterUseCase;
import dagger.Module;
import dagger.Provides;
import br.com.uol.pagseguro.smartcoffee.auth.AuthUseCase;
import br.com.uol.pagseguro.smartcoffee.transactions.TransactionsUseCase;

@Module
public class UseCaseModule {

    @Provides
    AuthUseCase providesAuthUseCase(PlugPag plugPag) {
        return new AuthUseCase(plugPag);
    }

    @Provides
    TransactionsUseCase providesTransactionsUseCase(PlugPag plugPag) {
        return new TransactionsUseCase(plugPag);
    }

    @Provides
    PrinterUseCase providesPrinterUseCase(PlugPag plugPag) {
        return new PrinterUseCase(plugPag);
    }
}
