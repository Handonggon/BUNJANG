package kr.co.study.bunjang.mvc.domain.home.model.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.domain.home.model.embedded.Modif;

public class ModifListener {

    @PrePersist
    public void prePersist(final Object object) {
        if (object instanceof ModifAccessible) {
            ModifAccessible accessModif = (ModifAccessible) object;
            Modif modif = accessModif.getModif();
            if (ObjUtils.isEmpty(modif)) {
                modif = new Modif();
                accessModif.setModif(modif);
            }
            modif.changeCreated();
        }
    }

    @PreUpdate
    public void preUpdate(final Object object) {
        if (object instanceof ModifAccessible) {
            ModifAccessible accessModif = (ModifAccessible) object;
            Modif modif = accessModif.getModif();
            if (ObjUtils.isEmpty(modif)) {
                modif = new Modif();
                accessModif.setModif(modif);
            }
            modif.changeModified();
        }
    }
}