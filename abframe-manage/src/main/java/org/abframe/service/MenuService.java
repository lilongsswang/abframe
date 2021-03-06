package org.abframe.service;

import org.abframe.dao.BaseDaoSupport;
import org.abframe.entity.Menu;
import org.abframe.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private BaseDaoSupport dao;

    public void deleteMenuById(int id) throws Exception {
        dao.save("MenuMapper.deleteMenuById", id);

    }

    public Menu getMenuById(PageData pd) throws Exception {
        return (Menu) dao.findForObject("MenuMapper.getMenuById", pd);
    }

    //取最大id
    public PageData findMaxId(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.findMaxId", pd);
    }

    public List<Menu> getParentMenu() throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.getParentMenu", null);

    }

    public void saveMenu(Menu menu) throws Exception {
        dao.save("MenuMapper.save", menu);
    }

    public List<Menu> getSubMenuByParentId(int parentId) throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.getSubMenuByParentId", parentId);

    }

    /**
     * @return
     * @throws Exception
     */
    public List<Menu> getAllMenu() throws Exception {
        List<Menu> parentMenu = this.getParentMenu();
        for (Menu menu : parentMenu) {
            List<Menu> subList = this.getSubMenuByParentId(menu.getId());
            menu.setSubMenu(subList);
        }
        return parentMenu;
    }

    public List<Menu> listAllSubMenu() throws Exception {
        return (List<Menu>) dao.findForList("MenuMapper.listAllSubMenu", null);

    }

    public PageData edit(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.update", pd);
    }

    /**
     * 保存菜单图标 (顶部菜单)
     */
    public PageData editicon(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.editIcon", pd);
    }

    /**
     * 更新子菜单类型菜单
     */
    public PageData editType(PageData pd) throws Exception {
        return (PageData) dao.findForObject("MenuMapper.editType", pd);
    }
}
