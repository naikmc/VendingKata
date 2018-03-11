package com.github.maheshnaik.vendingkata;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.github.maheshnaik.vendingkata.event.UpdateVendingMachine;
import com.github.maheshnaik.vendingkata.model.VendingMachineManager;
import com.github.maheshnaik.vendingkata.service.VendingMachineOperation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VendingActivity extends AppCompatActivity {

    @BindView(R.id.main_text)
    TextView displayWindow;

    @BindView(R.id.return_coins)
    TextView returnCoins;

    private AlertDialog dialogInsetCoins;

    private EditText insertCoinInput;

    public VendingMachineOperation vendingMachineOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending);
        ButterKnife.bind(this);
        vendingMachineOperation = new VendingMachineManager();

        this.insertCoinInput = new EditText(this);
        this.insertCoinInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.dialogInsetCoins = new AlertDialog.Builder(this)
                .setTitle("Insert Coins")
                .setMessage("Please Insert nickels, dimes, and quarters value only")
                .setView(this.insertCoinInput)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int usdValue = Integer.parseInt(VendingActivity.this.insertCoinInput.getText().toString());
                        vendingMachineOperation.insertCoins(usdValue);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.prod_chips)
    public void clickChips() {
        vendingMachineOperation.buyChips();

    }

    @OnClick(R.id.prod_candy)
    public void clickCandy() {
        vendingMachineOperation.buyCandy();
    }

    @OnClick(R.id.prod_cola)
    public void clickCola() {
        vendingMachineOperation.buyCola();
    }

    @OnClick(R.id.insert_coins)
    public void clickInsertCoins() {
        dialogInsetCoins.show();
    }


    @Subscribe
    public void onMessageEvent(UpdateVendingMachine event) {
        displayWindow.setText(vendingMachineOperation.getDisplayMessage());
        returnCoins.setText(vendingMachineOperation.getMoneyReturned()+"");
    }

}
