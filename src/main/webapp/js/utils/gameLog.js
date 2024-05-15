let url = window.location.href;
gameID =  url.substring(url.lastIndexOf("/gtmp/") + 6);
logs = []
divLogs = null

elementsReload()


function elementsReload()
{
    // reset of variables and state
    logs = []
    divLogs = document.getElementById("gameLog")
    divLogs.innerHTML = ""


    divLogs.disabled = true;
    genericGETRequest(contextPath + "game/logs/" + gameID, fillGameLog);
    document.getElementById("gameLog").disabled = false;
}

function fillGameLog(req)
{
    if (req.readyState === XMLHttpRequest.DONE)
    {
        if (req.status === HTTP_STATUS_OK)
        {
            logs = JSON.parse(req.responseText)[JSON_resource_list]
            creaTabella(logs)
        }
    }
}

function createButtonRound(round)
{
    return  '<button className="nav-link px-4 text-start mb-3 active" id="round-'+round+'-tab" data-bs-toggle="tab" data-bs-target="#round-'+round+'" type="button" role="tab" aria-controls="round-'+round+' "aria-selected="true">'+
                '<span className="d-block fs-5 fw-bold">' +
                    'Round ' + round +
                '</span>' +
            '</button>'
}
function createActionBlock(phase, subphase, typeOfAction, user, targhet)
{
    return  '<li className="d-flex flex-column flex-md-row py-4">' +
                '<span className="flex-shrink-0 width-13x me-md-4 d-block mb-3 mb-md-0 small text-muted">' +
                    phase + ', subphase '+ subphase +//day or night'+j+'
                '</span>' +
                '<div className="flex-grow-1 ps-4 border-start border-3">' +
                    '<h4>'+typeOfAction+'</h4>'+// type action
                    ' <p className="mb-0">'+
                        'the user '+user+' make the action '+typeOfAction+' on '+ targhet + //user , typeaction, targhet
                    '</p>'+
                '</div>'+
            '</li>'
}
function createContAction(round, actionBlocks)
{
    return  '<div class="tab-pane fade active show" id="round-'+round+'" role="tabpanel" aria-labelledby="round-'+round+'-tab">' +
                '<ul class="pt-4 list-unstyled mb-0">' +
                    actionBlocks +
                '</ul>' +
            '</div>'

}
function createCont(buttonsRound, ContAction)
{
    return  ' <div class="container py-9 py-lg-11 position-relative z-index-1">' +
            '    <div class="row">' +
        '           <div class="col-lg-5 mb-5 mb-lg-0">' +
            '            <div class="nav nav-pills flex-column aos-init aos-animate" id="tab" role="tablist" data-aos="fade-up">' +
                             buttonsRound + ''+
            '            </div>' +
                '        <div class="col-lg-7 col-xl-6">' +
                '            <div data-aos="fade-up" class="tab-content aos-init aos-animate" id="myTabContent">' +
                                ContAction +
                '            </div>' +
                '        </div>' +
        '           </div>' +
            '    </div>' +
            ' </div>'
}

function creaTabella(data)
{
    let key = ["player", "round", "phase", "subphase", "typeAction", "target"]
    let i = data.length > 0 ? Object.keys(data[0]) : [0]
    let bs = ''
    let as = ''
    let ca = ''
    let roundMax = data.length-1 >= 0 ? data[data.length-1][i[0]][key[1]] : 0
    let round = data.length > 0 ? data[0][i[0]][key[1]] : 0


    for (let j = 1; j<=roundMax; j++)
        bs = bs.concat(createButtonRound(j))

    for (let r of data)
    {
        if (round === r[i[0]][key[1]])
        {
            as = as.concat(createActionBlock(r[i[0]][key[2]], r[i[0]][key[3]], r[i[0]][key[4]], r[i[0]][key[0]], r[i[0]][key[5]]))
        }
        else
        {
            ca = ca.concat(createContAction(r[i[0]][key[1]], as))
            as = createActionBlock(r[i[0]][key[2]], r[i[0]][key[3]], r[i[0]][key[4]], r[i[0]][key[0]], r[i[0]][key[5]])
        }
        round = r[i[0]][key[1]]
    }
    if(ca ==="") ca = ca.concat(createContAction(round, as))


    divLogs.innerHTML = createCont(bs, ca)
}