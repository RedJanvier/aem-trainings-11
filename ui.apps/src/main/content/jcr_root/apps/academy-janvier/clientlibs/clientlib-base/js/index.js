const fetchPages = async () => {
    try {
        const res = await fetch("http://localhost:4502/bin/pages");
        const body = await res.json();
        document.querySelector("#my-pages").innerHTML = body
            .map(page => `<li><a href="/${page.path}">${page.title}</a></li>`)
            .join("");

    } catch (e) {
        console.log(e);
    }
}

fetchPages();