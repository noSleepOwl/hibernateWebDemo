let museum = {};

museum.museums = examplePages;

museum.currentPage = null;
museum.loadAfter = [];
museum.loadPage = function (func) {
    let next = func(),
        currentPage = museum.currentPage;
    if (currentPage && currentPage === next) {
        next = func();
    }
    museum.currentPage = next;

    $.post(next.path, function (page) {
        $("#form_context").html(page);
        museum.active(next.path);
        museum.loadAfter.filter(ele => {
            return ele && typeof ele === 'function';
        }).forEach(o => {
            o(next);
        });
    }, 'html')
};

museum.nextPage = function nextPage() {
    museum.loadPage(() => {
        let next = museum.museums.shift()
        museum.museums.push(next);
        return next;
    })
};

museum.next = () => {
    let next = museum.museums.pop()
    museum.museums.unshift(next);
    return next;
};

museum.prevPage = function prevPage() {
    museum.loadPage(museum.next);
};

museum.active = path => {
    $('#myAffix .list-group .list-group-item').each((index, ele) => {
        let current = $(ele).data('path');
        if (current === path) {
            $(ele).addClass("active");
        } else {
            $(ele).removeClass("active");
        }
    })
};
museum.createLinkElement = (id, isActivate, text) => {
    return ` <li class="${isActivate ? 'active' : ""}">
                <a href="#${id}">${text}</a>
             </li>`;
};
museum.initTitleLink = () => {
    function guid() {
        function S4() {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }

    let linkElement = '';

    $('#form_context .panel-heading').each((index, ele) => {
        let id = guid();
        $(ele).attr('id', id);
        linkElement += museum.createLinkElement(id, !linkElement, $(ele).text());
    })
    $(' #page_content_list').html(linkElement);
    $(' body').scrollspy('refresh')

};

museum.changeBrandName = function (page) {
    $('#brand_name').text(page.name);
};

$(() => {

    museum.museums.forEach(o => {
        o.path = webctx + o.path;
    });

    function createItem(name, isFirst, path) {
        return ` <a  data-path="${path}" class="list-group-item ${isFirst ? 'active' : ""}">${name}</a>`;
    }

    function eachMuse() {
        let items = '';
        museum.museums.forEach(ele => {
            items += createItem(ele.name, !items, ele.path);
        });
        return items;
    }

    $('#myAffix .list-group').html(eachMuse());

    $('#myAffix .list-group .list-group-item').each((index, ele) => {

        $(ele).click(e => {
            e.preventDefault();
            museum.loadPage(() => {
                let path = $(ele).data('path');
                let target = museum.next();
                while (path !== target.path) {
                    console.log(target, path);
                    target = museum.next();
                }
                return target;
            });
        });
    });

    $('#myAffix').affix({
        offset: {
            top: "30%"
            /*   bottom: function () {
                   //不超过footer 高度 + 165px 的位置
                   return (this.bottom = $('.footer').height() + 165);
               }*/
        }
    });
});