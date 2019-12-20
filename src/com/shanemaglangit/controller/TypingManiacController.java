package com.shanemaglangit.controller;

import com.shanemaglangit.model.TypingManiacModel;
import com.shanemaglangit.view.TypingManiacView;

public class TypingManiacController {
    private TypingManiacModel model;
    private TypingManiacView view;

    public TypingManiacController(TypingManiacModel model, TypingManiacView view) {
        this.model = model;
        this.view = view;

        initListeners();
    }

    private void initListeners() {
        view.getBtnStart().addActionListener(e -> startGame());
    }

    private void startGame() {
        System.out.println("Game Started");
    }


}
