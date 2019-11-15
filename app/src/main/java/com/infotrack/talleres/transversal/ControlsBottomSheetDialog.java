package com.infotrack.talleres.transversal;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infotrack.talleres.R;

import java.io.Serializable;
import java.util.Objects;

import butterknife.ButterKnife;

public class ControlsBottomSheetDialog extends BottomSheetDialogFragment {

    private static final String ACCIONES_TOOLBAR = "ACCIONES_TOOLBAR";
    private static final String ARRAY_ACCIONES_POR_MOSTRAR = "ACCIONES_MOSTRAR_BOTTOM_SHET";
    private static final String LAYOUT_ACCIONES = "LAYOUT_ACCIONES";
    private static final String ITEM_SELECCIONADO = "ITEM_SELECCIONADO";

    public static ControlsBottomSheetDialog obtenerInstancia(int layoutAcciones, int[] accionesPorMostrar, AccionesBottomSheet accionesBottomSheet, int posicionItemSeleccionado) {
        Bundle argumentos = new Bundle();
        argumentos.putInt(LAYOUT_ACCIONES, layoutAcciones);
        argumentos.putInt(ITEM_SELECCIONADO, posicionItemSeleccionado);
        argumentos.putSerializable(ACCIONES_TOOLBAR, accionesBottomSheet);
        argumentos.putIntArray(ARRAY_ACCIONES_POR_MOSTRAR, accionesPorMostrar);
        ControlsBottomSheetDialog fragmento = new ControlsBottomSheetDialog();
        fragmento.setArguments(argumentos);
        return fragmento;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AccionesBottomSheet acciones = (AccionesBottomSheet) Objects.requireNonNull(this.getArguments()).getSerializable(ACCIONES_TOOLBAR);
        int[] accionesPorMostrar = this.getArguments().getIntArray(ARRAY_ACCIONES_POR_MOSTRAR);
        View view = inflater.inflate(this.getArguments().getInt(LAYOUT_ACCIONES), container, false);
        ButterKnife.bind(this, view);

        LinearLayout contenedorPrincipal = (LinearLayout) view.getRootView();
        for (int i = 0; i < contenedorPrincipal.getChildCount(); i++) {
            LinearLayout contenedorHijo = (LinearLayout) contenedorPrincipal.getChildAt(i);

            if (contains(Objects.requireNonNull(accionesPorMostrar), i)) {
                int finalI = i;
                contenedorHijo.setOnClickListener(v -> {
                            Objects.requireNonNull(acciones).onBottomSheetClick(finalI, this.getArguments().getInt(ITEM_SELECCIONADO));
                            dismiss();
                        }
                );
                contenedorHijo.setVisibility(View.VISIBLE);
            }
        }
        return view;
    }

    public static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }
        return result;
    }

    public interface AccionesBottomSheet extends Serializable {
        void onBottomSheetClick(int posicionAccion, int itemSeleccionado);
    }
}
