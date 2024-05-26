<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/utils/navbar.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"
      type='text/css'>

<link href="https://fonts.googleapis.com/css2?family=Red+Hat+Display&display=swap" rel="stylesheet">

<%--<header>--%>
<%--    <h1>Lupus in Fabula</h1>--%>
<%--</header>--%>

<nav class="navbar navbar-expand-md shadow p-0 px-lg-3 py-lg-2 sticky-top mb-4">

    <a class="d-flex w-50 order px-3 py-2 p-lg-0 navbar-brand" href="<c:url value="/home"/>">
        <svg xmlns="http://www.w3.org/2000/svg" width="43" height="40" viewBox="0 0 42 40">
            <path style="fill:var(--text-color);"
                  d="M25.504-.012c.914.012 1.8.399 2.617.797.043.024.09.043.137.067.379.187.738.402 1.094.632l.156.098c.668.43 1.285.914 1.875 1.45q.165.148.336.292c.367.313.71.645 1.047.989q.13.136.257.265c.368.375.715.754 1.036 1.172q.199.256.421.5c.473.531.54 1.09.512 1.781-.043.602-.312 1.16-.668 1.637-.11.164-.144.23-.11.422.063.078.063.078.255.078.355-.008.625-.195.875-.441.199-.227.386-.47.511-.747.051-.09.051-.09.204-.195h.203c.476 1.078.629 2.16.222 3.293a5 5 0 0 1-1.261 1.774l-.149.136q-.232.212-.476.407c-.098.113-.11.207-.125.351.425.18.816.113 1.242-.031q.064-.018.129-.043a3.44 3.44 0 0 0 2.031-1.805c.281-.605.43-1.168.527-1.828.04-.234.082-.437.22-.633.16-.047.16-.047.304-.054l.222.414q.078.147.16.289c.368.62.63 1.41.633 2.14.004.14.004.14.063.282.055.187.062.34.062.535v1.035a1.3 1.3 0 0 1-.113.43c-.012.097-.02.199-.023.296a2.3 2.3 0 0 1-.114.61c-.07.23-.101.375-.02.61.106.132.22.253.337.378.215.25.37.535.535.824q.042.063.078.125c.578.899.805 1.973.977 3.012q.01.081.027.156l.023.145c.02.125.02.125.075.277q.006.218.007.434-.001.063.004.133v.273q-.001.208.004.418c0 .469-.023.898-.133 1.355a5 5 0 0 0-.105.723c-.031.262-.031.262-.086.375a6 6 0 0 0-.02.258 3.4 3.4 0 0 1-.144.746q-.062.224-.113.45a18 18 0 0 1-.645 1.995c-.137-.015-.27-.03-.41-.05-.016-.043-.027-.09-.043-.133-.137-.39-.277-.68-.57-.965-.051.015-.102.035-.157.05-.042.13-.078.25-.105.38-.016.058-.016.058-.027.117a3.7 3.7 0 0 0-.098.683c-.02.391-.121.75-.23 1.121-.024.09-.024.09-.051.18a14.9 14.9 0 0 1-2.406 4.79l-.083.109q-.138.182-.277.359l-.097.133c-.458.593-.985 1.156-1.59 1.593q-.088.063-.168.126c-.848.636-1.914 1.335-3.008 1.289-.07-.23-.031-.344.066-.559.227-.566.246-1.188.239-1.797q-.076-.026-.153-.05c-.265.171-.472.429-.676.671-.27.317-.546.594-.898.82a8 8 0 0 0-.367.258c-.473.34-.98.575-1.512.793l-.18.07a11.6 11.6 0 0 1-2.586.739l-.226.039c-.035.008-.07.012-.11.02-.03.003-.062.011-.093.015-.121.024-.121.024-.278.078a2.3 2.3 0 0 1-.566.067h-1.215q-.324-.001-.648.004h-.746a5.5 5.5 0 0 1-1.473-.176c-.09-.02-.18-.035-.266-.055-.824-.168-1.597-.398-2.347-.781l-.106-.055a9 9 0 0 1-1.586-1.043l-.129-.105c-.718-.575-.718-.575-.843-.89.011-.142.011-.142.05-.263.153-.074.301-.078.47-.101.41-.051.41-.051.812-.16.086-.024.086-.024.172-.055.496-.168.828-.426 1.187-.809.066-.07.137-.136.207-.207.32-.336.504-.699.688-1.125.027-.054.05-.113.078-.168.12-.289.097-.597.097-.906q-.001-.167.004-.332a3.47 3.47 0 0 0-.898-2.348c-.04-.039-.078-.082-.117-.12-.086-.098-.086-.098-.086-.204l-.098-.047c-.11-.058-.11-.058-.21-.16-.165.008-.235.027-.345.149q-.04.063-.082.125c-.16.226-.332.418-.597.507-.14-.02-.14-.02-.258-.05l.008-.188c.015-.808-.09-1.332-.653-1.941l-.172-.172c-.02-.05-.035-.102-.054-.156a3 3 0 0 0-.254-.106 5 5 0 0 1-.328-.226c-.664-.43-1.406-.575-2.18-.606l-.156-.008c-.52-.015-1.082 0-1.485.371-.199.22-.375.442-.46.73.007.153.027.294.05.442 0 .133 0 .133-.058.219-.207.156-.516.102-.762.102-.09.003-.09.003-.184.003-.511.004-.843-.144-1.246-.464a2.6 2.6 0 0 1-.515-.77c-.11-.14-.207-.187-.375-.234l-.125-.035q-.136-.04-.266-.075c-.273-.082-.434-.16-.617-.39q-.053-.052-.102-.106c-.008-.136-.008-.136 0-.261.184-.07.36-.11.555-.137.605-.106 1.16-.5 1.684-.809q.362-.215.73-.414c.055-.027.105-.055.164-.086 1.754-.926 3.555-1.172 5.5-.87q.148.022.3.034c.974.114 2.009.528 2.849 1.032.152.074.289.09.457.101-.063-.77-.536-1.394-1.079-1.902-.41-.348-.93-.555-1.43-.711l-.132-.047c-.352-.113-.688-.137-1.055-.148-.195-.016-.195-.016-.375-.067a1.9 1.9 0 0 0-.59-.066h-.105a2.1 2.1 0 0 0-.664.117c-.098.02-.2.031-.3.047a6.6 6.6 0 0 0-1.798.527q-.158.07-.312.133c-.23.098-.45.2-.668.316q-.172.089-.344.172a13 13 0 0 0-1.016.559c-.62.39-1.289.687-1.964.965-.036.015-.07.035-.11.05-.465.196-1.113.192-1.582-.003-.047-.024-.047-.024-.098-.047q-.122-.037-.238-.075c-.433-.148-.785-.43-1.144-.71a4 4 0 0 1-.149-.11q-.051-.048-.11-.098v-.105l-.089-.04c-.219-.124-.363-.339-.523-.534l-.106-.125a5.4 5.4 0 0 1-.578-.875c-.078-.137-.16-.266-.242-.399-.153-.254-.242-.52-.34-.8a2.5 2.5 0 0 0-.3-.602c-.224-.332-.325-.688-.434-1.07a2 2 0 0 0-.04-.13c-.054-.202-.093-.394-.062-.6.363-.446.844-.606 1.383-.731l.101-.055q.246-.04.496-.074c.168-.024.168-.024.325-.086.172-.055.328-.067.507-.074.258-.016.477-.051.723-.133l.27-.04c.316-.05.617-.136.922-.226.23-.066.457-.125.691-.176.434-.101.883-.222 1.242-.5.117-.05.238-.105.356-.156q.175-.083.343-.172c.063-.03.125-.066.192-.097l.094-.047.246-.13q.369-.18.726-.39c.57-.343 1.07-.535 1.727-.62.297-.04.582-.087.87-.161l.188-.047c.516-.18.856-.644 1.09-1.129q.024-.06.055-.125c.055-.12.129-.215.207-.324q.07-.118.133-.242a25 25 0 0 1 .488-.899 2.67 2.67 0 0 1 1.04-1.054c.07-.04.07-.04.136-.082.324-.192.652-.364.988-.532.281-.14.559-.289.836-.433.434-.23.867-.461 1.309-.684.277-.14.55-.289.824-.433q.575-.315 1.168-.594.406-.194.804-.399c.036-.015.067-.035.102-.05.09-.047.176-.094.266-.137.043-.024.09-.043.136-.066.18-.141.262-.293.325-.512.023-.254.023-.508.02-.766V6.66c-.005-.414-.005-.414-.126-.805q-.007-.152-.008-.304c-.035-.582-.226-1.13-.398-1.68-.016-.043-.027-.086-.043-.133a9.4 9.4 0 0 0-.621-1.488c-.059-.11-.113-.223-.172-.336a10 10 0 0 0-.645-1.121c-.058-.113-.058-.113-.043-.23.036-.094.036-.094.086-.145.641-.074 1.22.36 1.75.676.086.05.176.105.266.156.547.316 1.04.691 1.535 1.086q.134.107.274.215c.351.277.699.554.992.898q.063.082.133.156c.168.247.156.477.152.766v.16c0 .114-.004.223-.004.336q-.005.252-.004.504.001.165-.004.328v.153a4 4 0 0 1-.097.832c-.036.175-.04.343-.04.527v.195c.02.184.043.258.172.383.133.02.133.02.254 0q.022-.054.043-.113c.059-.145.059-.145.13-.254.331-.602.48-1.305.526-1.984.02-.208.02-.208.07-.399.083-.316.067-.637.067-.96V3.87a5.6 5.6 0 0 0-.133-1.219 5 5 0 0 1-.066-.816 2 2 0 0 0-.125-.633q-.017-.15-.027-.3c-.012-.2-.012-.2-.075-.38a5 5 0 0 1-.007-.238V.156c.015-.222.18-.164.363-.168m1.797 4.04a.6.6 0 0 0-.012.23v.12c.008.118.008.118.035.22.219.914-.215 2.02-.484 2.879-.031.093-.031.093-.059.187-.129.406-.285.797-.445 1.191q-.315.797-.59 1.61l-.07.191a6 6 0 0 0-.27 1.055h-.258l-.015-.188c-.04-.671-.04-.671-.293-1.273a1.3 1.3 0 0 0-.406-.21q-.075-.027-.157-.052-.111-.017-.222-.027l-.133-.012-.277-.023a3.5 3.5 0 0 1-.598-.098c-.23-.066-.453-.066-.692-.062q-.064.001-.128-.004a2.5 2.5 0 0 0-.715.12c-.25.032-.5.048-.75.063-.262.02-.489.051-.735.145a7 7 0 0 1-.297.055q-.281.044-.562.101l-.098.02a5 5 0 0 0-.422.148q-.145.06-.296.113c-.782.29-1.504.618-2.168 1.133l-.145.113c-.555.45-1.039.926-1.39 1.559l-.13.223c-.457.78-.457.78-.585 1.66.046.11.046.11.152.156.32-.012.547-.137.82-.297q.06-.03.117-.066c.23-.133.43-.274.625-.453.118-.11.25-.192.383-.282.11-.082.207-.168.309-.261v-.102l.09-.047c.37-.195.675-.508.984-.793.242-.218.484-.43.742-.629q.141-.11.282-.226c1.046-.856 1.046-.856 1.59-.817-.048.309-.048.309-.102.368-.016.152-.016.152 0 .312.09.113.09.113.203.117h.238c.14-.015.246-.062.375-.117q.167-.032.332-.05a3.7 3.7 0 0 0 .645-.157c.218-.027.37-.027.562.102.028.16.028.16.035.355 0 .055 0 .055.004.11l.012.32.004.168q.004.256-.004.511v.122c-.008.617-.07 1.136-.309 1.71l-.058.145a3.5 3.5 0 0 1-.469.738c-.078.098-.14.196-.207.301-.156.227-.371.348-.598.488q-.139.094-.285.188c-.601.39-1.238.672-1.96.687a2.7 2.7 0 0 0-.462.094c-.23.05-.468.043-.703.043-.191.008-.297.012-.457.125-.09.14-.097.219-.09.387q-.001.066.004.137c.004.12.004.12.063.222q.017.136.027.266l.016.144c0 .04.004.075.008.114a1.3 1.3 0 0 1-.497-.317l-.136-.129-.258-.25c-.742-.703-1.582-1.246-2.594-1.398-.12.016-.12.016-.203.055-.059.18-.059.246.024.421l.078.153v.207c-.258.012-.426.004-.664-.102-.278-.074-.559-.125-.84-.18q-.165-.036-.328-.085a3.6 3.6 0 0 0-.68-.086l-.129-.004c-.582-.024-1.121.008-1.68.191a9 9 0 0 1-.449.13c-.332.093-.656.202-.984.316a22 22 0 0 1-1.285.41c-.114.035-.215.07-.325.12-.168.079-.336.13-.515.184-.258.078-.52.164-.778.246q-.385.13-.777.23c-.125.06-.176.134-.246.25-.05.153-.055.259-.063.419-.054.61-.406 1.047-.793 1.484-.14.164-.199.278-.195.5.063.254.27.442.438.633l.113.137c.379.441.762.789 1.27 1.066q.053.03.105.067c.559.289 1.309.218 1.887.027q.314-.118.617-.254c.035-.012.066-.027.101-.043.192-.082.38-.176.567-.27l.16-.078c.23-.113.453-.23.664-.378.59-.418 1.25-.743 1.895-1.063q.144-.072.289-.148c.828-.41 1.664-.653 2.586-.641h.171c1.446.016 2.957.281 4.098 1.273.121.114.121.114.3.098.22-.066.294-.14.438-.316.024-.028.043-.055.067-.082.191-.246.27-.504.344-.809.503.133.75.656 1.003 1.086.399.723.739 1.52.774 2.36.008.113.008.113.066.222.063.188.067.36.063.555l.004.117q.001.124-.004.242-.001.189.004.371.001.119-.004.238.005.056.004.11c-.004.21-.032.367-.117.558q-.03.17-.051.34c-.051.403-.133.79-.258 1.176-.238.04-.363-.027-.559-.156-.234-.153-.39-.211-.672-.211-.12.043-.12.043-.203.105.028.239.078.38.215.575.215.328.352.675.488 1.043l.075.187c.195.516.34 1.027.406 1.574.023.227.023.227.098.438.054.86-.086 1.62-.41 2.414a2 2 0 0 0-.102.566c.074.094.074.094.203.156.383-.039.664-.25.973-.468.035-.028.074-.051.109-.078a8 8 0 0 0 1.07-.97l.106-.112c.281-.305.5-.625.715-.985l.058-.094a6 6 0 0 0 .403-.777c.02-.039.035-.078.054-.121.254-.586.254-.586.352-1.203.148-.004.23-.004.36.05.093.114.167.227.237.356a.9.9 0 0 0 .223.274c.106-.004.106-.004.207-.055.055-.113.055-.113.086-.254.024-.078.024-.078.043-.16l.043-.172c.262-.961.645-1.77 1.262-2.55q.165-.235.324-.47c.105-.152.215-.289.34-.418a3 3 0 0 0 .125-.23c.066-.133.133-.242.226-.356.153-.203.266-.425.38-.656l.07-.137c.125-.214.125-.214.171-.453l.41-.105.036.133.039.175c.02.086.02.086.043.172a.65.65 0 0 0 .09.254l.02.278c.015.222.042.375.183.558.203.008.308-.012.469-.144.726-.778 1.16-1.918 1.425-2.942q.025-.073.043-.148c.262-1.004.223-2.043.168-3.07l-.004-.23q.14-.008.285-.009l.16-.004c.169.012.169.012.333.063.207.055.402.07.613.078l.117.004q.128.001.25.008c.121 0 .246.008.371.011.43.012.832.004 1.25-.101l.172-.04c.871-.218 1.594-.566 2.238-1.214q.082-.083.168-.164c.328-.32.59-.625.805-1.04q.06-.099.125-.198c.352-.575.719-1.309.64-1.997-.054-.101-.054-.101-.152-.156-.242.004-.445.149-.648.27a6.2 6.2 0 0 1-1.281.527c-.078.027-.157.05-.235.078-.312.106-.597.14-.93.145q-.07-.001-.148.004h-.308q-.235.001-.47.008c-1.155.011-2.128-.391-2.972-1.208-.535-.582-.535-.582-.59-.976.063 0 .121-.004.184-.004.851-.027 1.617-.156 2.379-.57q.064-.036.129-.067c.265-.144.504-.32.742-.508l.152-.12q.053-.044.102-.09v-.106h.105v-.105q.076-.023.153-.051c.097-.133.097-.133.187-.297l.094-.164c.082-.172.129-.29.129-.48-.082-.16-.082-.16-.258-.262-.191-.028-.383-.02-.578-.016-.653-.004-1.188-.164-1.793-.398a19 19 0 0 0-.703-.262 21 21 0 0 0-.75-.254l-.102-.035c-.375-.117-.715-.14-1.105-.14h-.137c-.312 0-.617.023-.926.062-.047-.157-.058-.242-.004-.395l.07-.133.07-.132q.037-.063.067-.125.031-.06.055-.118c.066-.12.133-.14.254-.199l.09-.094a2.57 2.57 0 0 1 1.406-.683c.195-.031.281-.043.441-.172.079-.191.059-.316.008-.512l-.023-.136c-.215-1.012-.797-2.153-1.563-2.844-.039-.043-.082-.086-.12-.13-.657-.66-.657-.66-1.5-.987-.15.02-.184.054-.274.175M18.695 14.43l-.175.07c-.36.172-.68.375-.899.715-.031.14-.031.14 0 .262.242.25.656.168.977.171h.183c.414.004.817-.011 1.164-.257l.082-.094.082-.09c.125-.168.188-.305.192-.516.004-.05.004-.05.004-.101-.035-.164-.09-.219-.227-.317a2.4 2.4 0 0 0-.473-.023h-.128a1.9 1.9 0 0 0-.782.18M38.11 19.5l-.058.09q-.062.083-.125.164a1 1 0 0 0-.063.082c-.36.484-.797.785-1.3 1.09a1.4 1.4 0 0 0-.301.25c.066.207.125.297.304.41q.286.18.555.383a7 7 0 0 1 .523.41c.032.027.063.05.098.078.309.262.559.574.817.89.097.122.199.235.3.352.082.113.149.235.22.36.05.097.108.191.163.289.235.414.43.816.586 1.265a.95.95 0 0 0 .277.375c.176-.074.243-.136.317-.312.137-.375.121-.762.117-1.153-.004-.132 0-.261 0-.39 0-.438-.027-.817-.18-1.23q-.036-.173-.066-.344a7.2 7.2 0 0 0-1.266-2.848l-.093-.133c-.032-.039-.059-.078-.09-.117-.04-.05-.04-.05-.074-.098-.258-.18-.48-.093-.66.137m-3.894 3.871c-.082.254-.004.453.074.695.25.86.176 1.641-.23 2.446a4.1 4.1 0 0 1-.75.988c-.079.105-.079.105-.067.246.07.242.156.477.246.711.188.512.32 1.02.418 1.555l.035.164c.07.363.106.722.121 1.094 0 .043.004.09.004.136.036 1.13-.02 2.305-.55 3.32-.082.18-.067.274-.02.465.094.04.094.04.207.051 1.563-.847 2.469-3.18 2.961-4.797.363-1.246.547-2.652.266-3.933-.012-.067-.012-.067-.028-.133-.187-.754-.672-1.43-1.152-2.012l-.094-.12a3.6 3.6 0 0 0-.672-.614c-.074-.059-.074-.059-.144-.121-.203-.157-.38-.145-.625-.14m-3.48 3.86a1.8 1.8 0 0 0-.2.433 3.7 3.7 0 0 1-.558 1.055c-.075.086-.075.086-.063.195-.05.02-.102.035-.156.055-.086.078-.086.078-.164.18q-.042.045-.082.097c-.07.082-.07.082-.059.192l-.101.042c-.122.055-.122.055-.157.22l-.203.105c-.097.093-.097.093-.183.207l-.176.21q-.04.048-.074.094c-.082.063-.082.063-.188.059-.098-.047-.098-.047-.18-.23a4 4 0 0 1-.117-.465c-.062-.313-.191-.64-.414-.871-.133-.008-.133-.008-.258 0a9 9 0 0 0-.328.73 3.8 3.8 0 0 1-.441.79c-.54.812-.867 1.632-1.153 2.566q-.018.053-.03.105l-.09.285a5 5 0 0 1-.212.543q-.029.105-.05.211c-.172-.027-.172-.027-.36-.105-.066-.184-.066-.184-.101-.364-.051-.054-.051-.054-.102-.105-.133.015-.133.015-.258.05-.011.051-.027.102-.039.153-.207.758-.531 1.445-1.035 2.043q-.057.075-.113.144a3.9 3.9 0 0 1-.852.848l-.152.11-.145.101c-.132.113-.175.2-.222.367.226.235.457.266.765.313l.168.035c.91.09 1.84-.012 2.703-.313q.146-.051.286-.097c.675-.227 1.277-.582 1.863-.985l.09-.058c.093-.063.187-.13.277-.192a4 4 0 0 0 .3-.219v-.105l.09-.039c.13-.074.22-.16.325-.266q.058-.058.117-.12l.121-.126.121-.12.117-.122q.053-.051.106-.11c.09-.085.09-.085.078-.194h.102a6 6 0 0 0 .168-.266c.105-.176.21-.34.347-.492.164-.188.27-.39.383-.614q.105-.196.227-.382c.226-.383.367-.809.515-1.227l.063-.176a4.5 4.5 0 0 0 .222-.996c.024-.137.024-.137.075-.238.105-.832.261-2.266-.258-2.961-.149-.11-.223-.106-.356.02m0 0"/>
        </svg>
        <svg class="mx-2" xmlns="http://www.w3.org/2000/svg" width="82" height="40" viewBox="0 0 81 40">
            <path style="fill:var(--text-color);"
                  d="M9.652 1.113q.142.122.278.25c.027.028.054.051.082.078.703.66 1.406 1.512 1.582 2.497-.047.011-.047.011-.094.019-.941.223-1.832.531-2.668 1.031-.098.051-.098.051-.203.051l-.024-.148c-.12-.711-.34-1.278-.91-1.743a4 4 0 0 0-.468-.261c-.047-.024-.094-.043-.141-.067-.738-.336-1.508-.394-2.305-.457l-.008.11c-.02.27-.039.539-.066.808-.09.82-.094 1.637-.098 2.461v.59q-.005.869-.004 1.738l-.007 1.36v1.418c-.012 1.125-.387 2.027-1.063 2.906l.082-.035c1.23-.543 1.23-.543 1.84-.594q.064-.005.129-.016c.715-.035 1.434.18 2.125.34 1.008.235 1.82.188 2.723-.37.398-.263.746-.575 1.093-.9.141-.124.286-.25.43-.367.285.231.285.231.574.473-1.414 3.11-1.414 3.11-2.976 3.77a5 5 0 0 1-1.68.28H7.75c-.512-.003-1.02-.054-1.527-.1-1.797-.16-3.512-.196-5.29.18-.101.015-.101.015-.257.015a4 4 0 0 1-.242-.395q-.034-.053-.067-.113l-.16-.277.117-.118c.875-.894 1.192-1.93 1.192-3.16V9.953c0-.39.004-.781.004-1.172V6.297c0-.746-.122-1.383-.63-1.945-.109-.106-.109-.106-.253-.18L.52 4.094c-.02-.176-.02-.176 0-.367v-.133c.05-.13.05-.13.238-.215l.258-.074c1.097-.356 2.097-.938 3.09-1.52C7.795-.37 7.795-.37 9.651 1.113m8.494 18.837c.168.155.246.335.34.538.675 1.403 1.8 2.352 3.144 3.082-.016.172-.035.242-.16.364q-.07.045-.14.097a2 2 0 0 0-.157.11q-.087.054-.168.113-.077.051-.16.11-.328.221-.657.448-.533.364-1.054.75c-1.02-.515-1.621-1.48-1.98-2.546q-.105-.324-.204-.653c-.152 0-.21.09-.316.196-.727.734-1.086 1.496-1.106 2.539 0 .082 0 .082-.004.168q0 .263-.007.527l-.008.36c-.004.292-.012.59-.016.882q.088-.011.176-.015.814-.077 1.629-.149c.277-.027.558-.05.836-.078q.405-.034.808-.074.157-.011.309-.028c.145-.011.29-.027.434-.039l.125-.011c.296-.028.586-.032.882-.028l-.07.137c-.098.18-.191.355-.281.535q-.093.167-.18.332a49 49 0 0 0-.93 1.875q-.021.052-.05.106c-.043.097-.09.191-.133.289-.07.14-.07.14-.121.191a6 6 0 0 1-.367 0q-.013-.05-.032-.101c-.132-.38-.285-.598-.632-.79-.504-.218-1.122-.171-1.657-.187l-.203-.008c-.164-.004-.328-.012-.488-.015-.004.078-.004.078-.004.16-.004.5-.004 1-.012 1.5 0 .258-.004.511-.004.77-.015 2.386-.14 4.8-1.75 6.702l-.066.082c-.566.664-1.223 1.192-1.961 1.653h-.05a1.52 1.52 0 0 1 .195-1.04c.554-1.034.59-2.28.593-3.437v-.215q.005-.288.004-.578.005-.304.004-.61.007-.573.008-1.148c.004-.437.008-.87.008-1.308.008-.895.011-1.793.02-2.688h-.669c-.386 0-.77-.015-1.152-.054l.023-.477c0-.047.004-.09.004-.137a5 5 0 0 1 .075-.699c.19-.015.378-.035.566-.05l.16-.016c.332-.032.656-.043.992-.04-.066-1.234-.18-2.261-.886-3.304l.078-.047a52 52 0 0 0 1.664-1.035c.351-.227.707-.45 1.058-.676q.08-.045.16-.098l.313-.199q.394-.246.781-.492l.961-.605.168-.106q.16-.1.313-.2.505-.314 1.004-.636M31.246 5.406c.68.14 1.399.668 1.922 1.102l.14.113c.958.828 1.477 2.043 1.614 3.293.121 1.563-.11 3.074-1.129 4.313-.605.675-1.281 1.214-2.04 1.707q-.198.13-.386.277c-.433.332-.433.332-.699.312a2 2 0 0 1-.461-.25l-.086-.058c-.61-.422-.941-.86-1.32-1.516-.016 1.781-.031 3.567-.051 5.406h-2.703c0-.507 0-.507-.004-1.023 0-1.117-.004-2.23-.008-3.348l-.008-2.027q.001-.885-.004-1.77L26.02 11v-.879c0-.11-.004-.215-.004-.324v-.57a2.3 2.3 0 0 0-.176-.829l-.055-.156c-.09-.058-.09-.058-.207-.105l-.152-.082a1.3 1.3 0 0 0-.418-.13c.004-.155.008-.273.05-.417.126-.102.212-.16.352-.227q.054-.03.11-.058l.355-.176c.082-.043.164-.082.242-.125q.245-.123.485-.246c.293-.149.574-.305.859-.461l.266-.149q.06-.033.125-.066.445-.25.898-.488v1.156a8.6 8.6 0 0 0 2.34-1.156q.074-.052.156-.106M29.54 7.531c-.262.059-.52.102-.789.133l-.012 1.52q.001.353-.004.707-.005.338-.004.683 0 .129-.003.258c-.012.996.187 1.848.89 2.59.559.555 1.192.816 1.969.808q.058.005.121.004c.184 0 .348-.011.527-.062.442-1.55.504-3.238-.246-4.707a6.5 6.5 0 0 0-1.031-1.32c-.047-.051-.047-.051-.098-.102-.367-.398-.367-.398-.836-.598-.168.012-.32.047-.484.086m5.71 12.625v7.14c.93-.44 1.68-.913 2.32-1.714.188-.215.188-.215.34-.266a1.8 1.8 0 0 1 .406.04l.192.023c.875.133 1.703.508 2.27 1.219.847 1.207 1.027 2.66.788 4.101-.261 1.403-.808 2.703-1.742 3.79q-.042.05-.086.109c-.25.304-.539.543-.851.78-.04.036-.082.067-.125.102a5.1 5.1 0 0 1-1.594.793l-.117.036c-.352.101-.352.101-.504.015-.102-.12-.102-.12-.211-.277-.863-1.215-1.953-1.992-3.39-2.352-.055-.011-.055-.011-.118-.027l-.281-.07v-.157l-.012-3.761q-.007-.914-.008-1.82c0-.528-.004-1.06-.004-1.587q-.004-.419-.003-.84c0-.261 0-.527-.004-.788v-.293c.02-.774.02-.774-.297-1.457-.219-.176-.457-.274-.715-.375-.004-.165-.004-.165.055-.368.144-.125.312-.203.484-.289l.312-.164.172-.086c.274-.14.543-.289.817-.437l.277-.153c.426-.23.852-.46 1.277-.699.293-.168.293-.168.352-.168m0 8.086q-.007.48-.008.961-.005.165-.004.328c-.015 1.117.032 2.215.844 3.067.8.773 1.961.863 3.016.84.066.003.066.003.136.003a.8.8 0 0 0 .328-.054c.122-.14.16-.297.211-.473l.047-.164c.262-1.066.086-2.05-.465-2.984a3.6 3.6 0 0 0-.363-.473l-.094-.11a3.58 3.58 0 0 0-2.347-1.113c-.457-.015-.856.059-1.301.172m8.004-2.887q.62-.012 1.234-.02l.418-.003q.304-.007.602-.008.099-.005.191-.004c.16 0 .309 0 .469.035.14.153.14.153.207.313l.05.125q.019.054.036.113c.02.055.02.055.039.114.145.566.102 1.125-.004 1.69q-.011.077-.027.153c-.114.614-.266 1.215-.414 1.82l-.13.516-.077.317c-.172.687-.317 1.492.058 2.136.25.418.7.625 1.157.743.261.054.519.062.789.058h.144c.563-.008.977-.152 1.469-.418.133-.07.133-.07.289-.12v-7.56h2.754q.005.328.004.672.001 1.094.008 2.196c0 .445.003.886.003 1.332l.004 1.16.004.613v.582c.004.102 0 .207 0 .313.008.46.059.859.38 1.21a2.2 2.2 0 0 0 .64.266c-.012.235-.035.344-.188.528a3 3 0 0 1-.172.148c-.046.043-.046.043-.093.082-.227.207-.465.398-.703.594a8 8 0 0 0-.47.418l-.081.074q-.167.153-.32.316c-.09.094-.09.094-.262.203-.457-.035-.817-.37-1.102-.703-.203-.293-.289-.629-.355-.976-.922.168-1.754.625-2.567 1.078q-.053.029-.105.062a3.5 3.5 0 0 0-.563.395c-.148.105-.215.145-.394.14a3 3 0 0 1-.477-.152l-.156-.054c-.961-.36-1.676-1.07-2.105-2.004-.688-1.7-.364-3.535.003-5.266l.024-.113.066-.305c.18-.902.121-1.75-.203-2.613l-.039-.11zM36.602 5.563q.615-.011 1.23-.016.21-.001.422-.008.298-.005.601-.004c.063-.004.125-.004.188-.008.164 0 .312.004.469.035.14.153.14.153.207.317.02.039.035.082.054.125l.036.113c.015.055.015.055.035.113.148.567.105 1.122 0 1.692-.008.05-.02.098-.028.152-.117.61-.265 1.215-.418 1.817l-.128.515q-.037.159-.079.317c-.168.691-.312 1.496.063 2.136.25.422.695.63 1.156.746.262.051.52.063.785.06h.145c.562-.013.976-.157 1.472-.419.133-.07.133-.07.286-.121V5.563h2.757v.671c.004.735.004 1.465.008 2.2q.005.662.004 1.332c.004.386.004.773.008 1.16v.613q-.001.292.004.578v.313c.004.465.059.863.375 1.215.207.128.41.203.64.265-.007.235-.03.344-.183.524-.059.054-.117.101-.172.152-.047.043-.047.043-.094.082-.23.203-.468.398-.707.594a9 9 0 0 0-.547.492 4 4 0 0 0-.324.316c-.086.09-.086.09-.262.203-.453-.035-.812-.37-1.097-.707-.207-.289-.293-.625-.356-.972-.922.164-1.757.625-2.566 1.078l-.106.058a4 4 0 0 0-.566.399c-.144.105-.21.144-.394.14a4 4 0 0 1-.477-.152l-.152-.055c-.965-.363-1.676-1.074-2.11-2.007-.683-1.7-.363-3.532.004-5.262l.024-.113q.035-.152.066-.305c.18-.906.125-1.75-.2-2.613q-.021-.053-.042-.114-.016-.04-.031-.085m-22.93 0q.62-.011 1.234-.016.209-.001.418-.008.304-.005.602-.004c.062-.004.129-.004.191-.008.16 0 .309.004.469.035.14.153.14.153.207.317q.026.06.05.125l.036.113.039.113c.145.567.102 1.122-.004 1.692l-.027.152c-.114.61-.266 1.215-.414 1.817l-.13.515q-.041.159-.077.317c-.172.691-.317 1.496.058 2.136.25.422.7.63 1.156.746.262.051.52.063.79.06h.144c.563-.013.977-.157 1.469-.419.133-.07.133-.07.289-.121V5.563h2.754q.005.333.004.671c0 .735.004 1.465.008 2.2l.003 1.332.004 1.16c0 .203.004.41.004.613v.891c.008.465.059.863.38 1.215a2.2 2.2 0 0 0 .64.265c-.012.235-.035.344-.188.524-.054.054-.113.101-.172.152-.047.043-.047.043-.093.082-.23.203-.465.398-.703.594a8 8 0 0 0-.47.418q-.04.034-.081.074c-.11.101-.219.203-.32.316-.09.09-.09.09-.262.203-.457-.035-.817-.37-1.102-.707-.203-.289-.289-.625-.355-.972-.922.164-1.754.625-2.567 1.078l-.105.058a4 4 0 0 0-.563.399c-.148.105-.215.144-.394.14a3 3 0 0 1-.477-.152l-.156-.055c-.961-.363-1.676-1.074-2.106-2.007-.687-1.7-.363-3.532.004-5.262l.024-.113.066-.305c.18-.906.121-1.75-.203-2.613q-.02-.053-.039-.114zm59.633-.103c.539.169.976.411 1.3.892.079.156.145.312.207.472 1.051-.258 2.141-.558 3.004-1.242.13-.09.192-.125.352-.121.156.039.293.09.441.156.059.028.118.051.18.078.887.422 1.492 1.04 1.848 1.97.925 2.6.16 5.886-.313 8.503H76.79l.52-1.313q.14-.367.27-.734c.03-.09.03-.09.066-.184.851-2.34.851-2.34.652-4.75a5 5 0 0 0-.207-.34c-.055-.081-.055-.081-.055-.187a1 1 0 0 1-.082-.039c-.125-.066-.125-.066-.266-.168-.578-.363-1.433-.422-2.097-.297a4 4 0 0 0-.57.192c-.086.035-.168.066-.258.105v7.715H72.06c-.004-.332-.004-.332-.004-.668q-.001-1.089-.008-2.184l-.008-1.32-.004-1.156q-.005-.304-.004-.61.001-.285-.004-.578v-.308c-.004-.426-.043-.781-.27-1.149a1.15 1.15 0 0 0-.44-.258 3 3 0 0 0-.169-.066 2 2 0 0 1-.128-.05c.003-.239.027-.368.199-.54.062-.058.129-.113.191-.168l.102-.09c.093-.085.191-.168.285-.25l.117-.105q.148-.133.3-.266c.06-.047.114-.097.173-.148.156-.133.308-.266.465-.402q.223-.196.453-.391m-7.904 19.684q.304.228.602.457a21.4 21.4 0 0 0 2.832 1.851q.089.04.184.074c.125.075.171.11.21.246.016.149.016.29.012.438v.168q.001.275-.004.55-.001.195.004.387l-.004 1.012q-.006.81 0 1.617l-.004.567v.347l-.004.157c.004.39.047.738.301 1.047.309.253.574.312.969.374a1.2 1.2 0 0 1-.055.524c-.172.172-.379.285-.59.402q-.162.099-.332.196a3 3 0 0 1-.164.093 9 9 0 0 0-.605.407c-.129.058-.129.058-.266.02-.152-.083-.277-.184-.414-.29l-.176-.14-.101-.079q-.31-.241-.625-.476L67.043 35a6 6 0 0 0-.602-.406c-.07.054-.07.054-.14.105a18 18 0 0 1-2.258 1.469 2 2 0 0 0-.238.148c-.18.094-.293.051-.485.004-.77-.242-1.39-.613-1.785-1.336-.41-.847-.37-1.77-.082-2.648.371-.922 1.223-1.36 2.078-1.73.442-.184.89-.352 1.34-.52.117-.047.238-.09.356-.137q.252-.093.5-.191l.148-.055q.064-.022.133-.05.192-.071.383-.153c.027-.566-.004-1.008-.293-1.512-.29-.27-.29-.27-.645-.43a1.3 1.3 0 0 0-.398.208 5 5 0 0 1-.64.32q-.06.026-.126.05c-1.644.68-1.644.68-2.227.618-.132-.059-.171-.164-.234-.293-.094-.172-.21-.324-.324-.48a2 2 0 0 1 .148-.063c1.133-.484 2.13-1.07 3.024-1.934l.097-.093c.239-.23.434-.48.63-.746m.387 5.52-.168.077c-.312.145-.61.297-.894.492q-.064.044-.133.086a2.34 2.34 0 0 0-.723 1.66c.016.34.078.59.29.864.312.246.632.41 1.034.433.18-.039.18-.039.356-.109.058-.023.113-.043.176-.066.242-.106.48-.23.714-.348v-3.36c-.203 0-.468.188-.652.27m-40.419-5.519.602.457a21 21 0 0 0 2.707 1.78l.12.071q.095.04.188.074c.125.075.168.11.211.246.012.149.012.29.012.438v.168q.001.275-.004.55v.387c.004.336 0 .672 0 1.012q-.005.81-.004 1.617v.914q-.005.075-.004.157c0 .39.047.738.297 1.047.313.253.578.312.969.374.02.188.012.344-.05.524-.177.172-.38.285-.59.402l-.333.196-.164.093q-.314.189-.605.407c-.13.058-.13.058-.266.02a3 3 0 0 1-.414-.29l-.18-.14c-.031-.028-.062-.051-.097-.079a38 38 0 0 0-.754-.57 6 6 0 0 0-.602-.406c-.07.054-.07.054-.14.105a18 18 0 0 1-2.258 1.469 2 2 0 0 0-.239.148c-.18.094-.293.051-.484.004-.77-.242-1.394-.613-1.785-1.336-.414-.847-.371-1.77-.086-2.648.375-.922 1.223-1.36 2.082-1.73a38 38 0 0 1 1.34-.52q.174-.069.355-.137.25-.093.5-.191l.149-.055q.063-.022.133-.05a10 10 0 0 0 .382-.153c.028-.566-.004-1.008-.293-1.512-.289-.27-.289-.27-.644-.43a1.3 1.3 0 0 0-.399.208 5 5 0 0 1-.64.32c-.043.016-.082.035-.13.05-1.644.68-1.644.68-2.222.618-.133-.059-.172-.164-.234-.293-.098-.172-.211-.324-.324-.48q.072-.035.144-.063c1.137-.484 2.133-1.07 3.028-1.934q.045-.046.097-.093c.235-.23.434-.48.63-.746m.387 5.52-.168.077c-.313.145-.61.297-.895.492l-.133.086a2.34 2.34 0 0 0-.722 1.66c.015.34.078.59.285.864.313.246.637.41 1.04.433.179-.039.179-.039.35-.109l.18-.066c.242-.106.477-.23.715-.348v-3.36c-.203 0-.469.188-.652.27M53.965 5.563c.047.023.094.042.14.066 2.77 1.242 2.77 1.242 3.137 1.984a2 2 0 0 0-.129.043c-.656.246-1.199.578-1.758.992l-.453.329-.054-.102c-.426-.777-1.153-1.16-1.973-1.422-.61-.156-1.363-.289-1.977-.105-.125.175-.187.375-.261.578q-.017.054-.04.11a2.03 2.03 0 0 0 .145 1.519c.281.492.863.734 1.383.898 1.55.379 3.219.094 4.7-.426.5.996 1.01 2.348.722 3.47-.344 1-1.18 1.558-2.067 2.026-.773.372-1.566.606-2.398.801-.07.02-.07.02-.137.035-.539.125-.953-.015-1.46-.191l-.188-.063a14 14 0 0 1-1.594-.671l-.11-.055c-.558-.277-1.124-.59-1.503-1.102.07-.02.07-.02.14-.043a7.8 7.8 0 0 0 2.051-1.011c.117-.078.23-.145.356-.203.027.035.05.07.078.109.695.984 1.488 1.617 2.68 1.883.289.047.574.062.87.062h.122c.336 0 .656-.043.98-.113.035-.723-.012-1.434-.52-1.996-.792-.723-1.917-.969-2.96-.996-.047 0-.09-.004-.137-.004-.71-.02-1.426-.035-2.105.223-.172.05-.247.066-.41-.008a4 4 0 0 1-.258-.383c-.024-.035-.043-.074-.067-.113-.469-.797-.863-1.672-.683-2.618.265-.964 1.011-1.597 1.84-2.086a21 21 0 0 1 2.39-1.168c.156-.074.316-.14.477-.207l.152-.07c.348-.144.539-.137.879.027m4.418 14.594v.207l.012 4.864v.718c.003.645.003 1.285.003 1.93l.004 1.98.004 1.22v1.323c0 .172.004.348.004.524v.152c.004.414.09.785.375 1.098.297.183.606.273.95.316-.012.23-.028.446-.192.617a4 4 0 0 1-.36.223c-.09.05-.18.106-.269.16l-.137.078c-.207.121-.406.25-.609.38l-.207.128-.148.094a15 15 0 0 1-1.008-.727q-.137-.11-.274-.214c-.062-.051-.062-.051-.129-.102-.144-.113-.144-.113-.3-.187-.2-.11-.38-.215-.454-.446-.054-.316-.039-.637-.035-.957v-.324q-.001-.438.004-.883.005-.458.004-.922-.001-.875.004-1.75.01-1.563.008-3.133c.004-.367.004-.73.008-1.097v-.684l.004-.316v-.434q.004-.062.003-.125c0-.355-.082-.672-.27-.972-.21-.207-.46-.29-.737-.375-.004-.165-.004-.165.05-.368.149-.125.317-.203.485-.289l.316-.164.168-.086c.278-.14.547-.289.817-.437l.28-.153c.427-.23.852-.46 1.274-.699.297-.168.297-.168.352-.168M66.965 5.34c.113.027.207.066.308.12.036.013.067.024.102.04.488.207.844.598 1.086 1.063.164.417.18.824.18 1.273v1.239q.005.915.004 1.831l.003 1.012c0 .18.004.36 0 .535l.004.598v.176c.004.441.075.82.375 1.152.297.187.606.273.95.32-.016.227-.028.446-.192.617a4 4 0 0 1-.36.223c-.09.05-.179.106-.269.156-.066.04-.066.04-.136.078q-.31.183-.61.383-.106.064-.207.13l-.148.093c-.348-.227-.68-.473-1.008-.73l-.274-.211-.128-.106c-.145-.113-.145-.113-.301-.176a.97.97 0 0 1-.422-.351c-.11-.438-.07-.91-.063-1.36.004-.133.004-.27 0-.402q.001-.479.008-.957.01-.902.008-1.809l.008-.593V9.32a2 2 0 0 0 .004-.164c.004-.398-.055-.726-.266-1.066a1.1 1.1 0 0 0-.437-.258l-.172-.066q-.064-.023-.13-.051c.02-.293.075-.426.286-.63l.14-.14.153-.144c.098-.094.195-.192.293-.285l.14-.137c.176-.176.34-.363.497-.55.386-.466.386-.466.574-.49M68.68.578v2.57c-.961.485-.961.485-1.309.645-.062.027-.062.027-.121.055-.078.039-.16.074-.242.113l-.371.172q-.118.051-.235.11-.056.021-.113.05c-.258.117-.258.117-.312.117V1.785c.34-.172.683-.34 1.035-.496.066-.031.066-.031.14-.062l.29-.13q.224-.1.449-.195l.28-.129c.048-.02.09-.039.138-.058l.125-.055.109-.047c.082-.035.082-.035.137-.035m0 0"/>
        </svg>
    </a>
    <button class="border-0 px-3 py-2 p-lg-0 navbar-toggler collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#headerNavbar"
            aria-controls="headerNavbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="headerNavbar" class="justify-content-center bg-blue order-2 w-50 navbar-collapse collapse">
        <div class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link mx-3" aria-current="page" href="<c:url value="/home"/>">
                    Home
                </a>
            <li class="nav-item">
                <a class="nav-link mx-3" href="<c:url value="/habitant/"/>">Stats&History</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle mx-3" href="#" role="button" data-bs-toggle="dropdown"
                   aria-expanded="false">
                    Game
                </a>
                <ul class="dropdown-menu mx-2 mx-md-0">
                    <li><a class="dropdown-item" href="<c:url value="/rules"/>">Rulebook</a></li>
                    <hr class="dropdown-divider">
                    <li><a class="dropdown-item" href="<c:url value="/newVillage"/>">Create a village</a></li>
                    <li><a class="dropdown-item" href="<c:url value="/"/>">Join your village</a></li>
                </ul>
            </li>
            <div class="d-flex d-md-none pb-2 text-center nav-item m-2">
                <span class="navigation__group">
                    <img class="profile" src="${pageContext.request.contextPath}/media/favicon.ico"
                         alt="Profile Picture">
                </span>
            </div>
        </div>
    </div>

    <div class="d-flex w-50 order-last px-3 py-2 p-lg-0 mx-0 navbar-brand justify-content-end d-none d-md-flex">
        <span class="navigation__group">
            <img class="profile" src="${pageContext.request.contextPath}/media/favicon.ico" alt="Lupus logo">
        </span>
    </div>
    <div class="dropdown__wrapper hide dropdown__wrapper--fade-in none">
        <div class="dropdown__group">
            <c:if test="${not empty sessionScope.user}">
                <div class="user-name">${sessionScope.user.getUsername()}</div>
                <div class="email">${sessionScope.user.getEmail()}</div>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <div class="user-name">Not Logged In</div>
            </c:if>
        </div>
        <hr class="divider">
        <nav class="profile_nav">
            <ul>
                <li>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="var(--nabbar-inverted-bg)"
                         viewBox="0 0 448 512">
                        <path d="M304 128a80 80 0 1 0 -160 0 80 80 0 1 0 160 0zM96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM49.3 464H398.7c-8.9-63.3-63.3-112-129-112H178.3c-65.7 0-120.1 48.7-129 112zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3z"/>
                    </svg>
                    <a href="${pageContext.request.contextPath}/habitant/me"> My Profile</a>
                </li>
                <li>
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="var(--nabbar-inverted-bg)"
                         viewBox="0 0 512 512">
                        <path d="M448 256c0-106-86-192-192-192V448c106 0 192-86 192-192zM0 256a256 256 0 1 1 512 0A256 256 0 1 1 0 256z"/>
                    </svg>
                    <div class="dropup">
                        <a class="dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Theme
                        </a>
                        <ul class="dropdown-menu" id="theme" style="font-size: 0.95rem; padding: 0;">
                            <li><a class="dropdown-item" theme="light">Light</a></li>
                            <li><a class="dropdown-item" theme="dark">Dark</a></li>
                            <li><a class="dropdown-item" theme="auto">Auto</a></li>
                            <li><a class="dropdown-item active" theme="dynamic">Dynamic</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
            <hr class="divider">
            <ul>
                <li style="color: #E3452F;">
                    <c:if test="${not empty sessionScope.user}">
                        <img src="${pageContext.request.contextPath}/media/navbar/logout.svg" alt="LogOut"><a
                            href="/lupus/logout">Logout</a>
                    </c:if>
                    <c:if test="${empty sessionScope.user}">
                        <img src="${pageContext.request.contextPath}/media/navbar/logout.svg" alt="LogIn"><a
                            href="/lupus/login">Login</a>
                    </c:if>
                </li>
            </ul>
        </nav>
    </div>

</nav>

<script src="${pageContext.request.contextPath}/js/navbar.js"></script>