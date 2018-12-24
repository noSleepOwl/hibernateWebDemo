///<reference path="app-tools.ts"/>

namespace museum {
    export let museums: Page[] = examplePages;
    let currentPage: Page | null = null;
    let loadAfter: Function[] = [];

    export function loadPage(func: () => Page) {
        let next = func();
        if (currentPage && currentPage === next) {
            next = func();
        }
        currentPage = next;
        $.post(next.path, function (page) {
            $("#form_context").html(page);
            active(next.path);
            loadAfter.filter(ele => {
                return ele && typeof ele === 'function';
            }).forEach(o => {
                o(next);
            });
        }, 'html')
    }

    function nextPage(): void {
        loadPage(() => {
            let next: Page = <Page>museums.shift();
            museums.push(next);
            return next;
        })
    }

    function prevPage(): void {
        loadPage(next);
    }

    export function next(): Page {
        let next = <Page> museum.museums.pop()
        museum.museums.unshift(next);
        return next;
    }

    function active(path): void {
        $('#myAffix .list-group .list-group-item').each((index, ele) => {
            let current = $(ele).data('path');
            if (current === path) {
                $(ele).addClass("active");
            } else {
                $(ele).removeClass("active");
            }
        })
    }

    function createLinkElement(id, isActivate, text): string {
        return ` <li class="${isActivate ? 'active' : ""}">
                <a href="#${id}">${text}</a>
             </li>`;
    }

    function initTitleLink(): void {
        let linkElement = '';
        $('#form_context .panel-heading').each((index, ele) => {
            let id = Tools.guid();
            $(ele).attr('id', id);
            linkElement += createLinkElement(id, !linkElement, $(ele).text());
        })
        $(' #page_content_list').html(linkElement);
        $(' body').scrollspy('refresh')

    }

    function changeBrandName(page: Page): void {
        $('#brand_name').text(page.name);
    }
}

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
        offset: {top: "30%"}
    });
});