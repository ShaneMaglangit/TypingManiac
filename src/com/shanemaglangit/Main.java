package com.shanemaglangit;

import com.shanemaglangit.controller.TypingManiacController;
import com.shanemaglangit.model.TypingManiacModel;
import com.shanemaglangit.model.WordRun;
import com.shanemaglangit.view.TypingManiacView;

public class Main {

    public static void main(String[] args) {
        TypingManiacModel model = new TypingManiacModel();
        TypingManiacView view = new TypingManiacView();
        TypingManiacController controller = new TypingManiacController(model, view);
    }
}

