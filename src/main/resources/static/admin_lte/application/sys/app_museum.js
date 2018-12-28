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
    museumItem: (href, icon, name, active, haveSub, subHtml) => {
        let subEle = '';
        if (haveSub && subHtml) {
            subEle = `<ul class="treeview-menu">
                   ${subHtml}
                </ul>`;
        }
        return ` <li class="${active ? "active" : ''} ${haveSub ? 'treeview' : ''}"><a href="${href}" data-toggle="ajaxLoad"><i class="${icon}"></i> ${name}</a>${subHtml}</li>`;
    },
    /**
     * 菜单数据加载
     */
    loadData: (back) => {
        $.post(tools.paths.sys.museum, function (data) {
            if (tools.isFunc(back)) {
                back(data);
            }
        });
    },
    /**
     * 菜单创建加载
     */
    createMuseum: () => {
        appMuseum.loadData((data) => {
            if (data.status === 0 || !data.data || data.data.length === 0) {
                return;
            }
            let root = appMuseum.root,
                html = appMuseum.parseDataToHtml(data.data);
            root.html(html);
        });
    },
    /**
     * 生成菜单html
     */
    parseDataToHtml: (data) => {
        let html = [];
        if (data && data.length > 0) {
            $.each(data, function (index, itemData) {
                let result = '',
                    subHtml = '',
                    subMuseum = itemData.subMuseum,
                    haveSub = subMuseum && subMuseum.length > 0;
                if (haveSub) {
                    subHtml = appMuseum.parseDataToHtml(subMuseum);
                }
                if (itemData.type === appMuseum.TYPE_LABEL) {
                    result = appMuseum.museumLabel(itemData.name) + subHtml;
                }
                if (itemData.type === appMuseum.TYPE_PARENT) {
                    result = appMuseum.museumTreeView(false, false, itemData.icon, itemData.name, itemData.pullRightIcon, subHtml);
                }
                if (itemData.type === appMuseum.TYPE_ITEM) {
                    result = appMuseum.museumItem(itemData.href, itemData.icon, itemData.name, false, haveSub, subHtml);
                }
                html.push(result);
            });
        }

        return html.join("");
    }
};

