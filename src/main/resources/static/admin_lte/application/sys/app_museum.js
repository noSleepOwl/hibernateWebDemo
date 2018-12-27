//菜单初始化
let appMuseum = {
    root: $('.main-sidebar [data-widget="tree"]'),
    TYPE_ITEM: "TYPE_ITEM",
    TYPE_PARENT: "TYPE_PARENT",
    TYPE_LABEL: "TYPE_LABEL",
    museumLabel: name => {
        return ` <li class="header">${name}</li>`;
    },
    /**
     *
     * @param active 是否激活
     * @param open 是否展开
     * @param icon 图标
     * @param name 名称
     * @param pullRightIcon 最小化后展示图标
     * @param view 内容html
     * @returns {string}
     */
    museumTreeView: (active, open, icon, name, pullRightIcon, view) => {
        return ` <li class="${active ? 'active' : ''} treeview ${open ? 'menu-open' : ''}">
                <a href="#">
                    <i class="${icon}"></i>
                    <span>${name}</span>
                    <span class="pull-right-container">
                            <i class="${pullRightIcon} pull-right"></i>
                        </span>
                </a>
                <ul class="treeview-menu">
                   ${view}
                </ul>
            </li>`
    },
    /**
     *
     * @param href 地址
     * @param icon 图标
     * @param name 名称
     * @param active 是否激活
     * @returns {string}
     */
    museumItem: (href, icon, name, active) => {
        return ` <li class="${active ? "active" : ''}"><a href="${href}" data-toggle="ajaxLoad"><i class="${icon}"></i> ${name}</a></li>`;
    },
    /**
     * 菜单数据加载
     */
    loadData: () => {

    },
    createMuseum: () => {
        let data = appMuseum.loadData(),
            root = appMuseum.root,
            html = appMuseum.parseDataToHtml(data);
        root.html(html);
    },
    /**
     * 创建菜单
     */
    parseDataToHtml: (data) => {
        let html = [];
        if (!data) {
            return "";
        }
        $.each(data, function (index, item) {
            let result = '';
            if (item.type === appMuseum.TYPE_LABEL) {
                result = appMuseum.museumLabel(item.name);
            }
            if (item.type === appMuseum.TYPE_PARENT) {
                let items = appMuseum.parseDataToHtml(data.items);
                result = appMuseum.museumTreeView(false, false, item.icon, item.name, item.pullRightIcon, items);
            }
            if (item.type === appMuseum.TYPE_ITEM) {
                result = appMuseum.museumItem(item.href, item.icon, item.name, false);
            }
            html.push(result);
        });
        return html.join("");
    }
};

