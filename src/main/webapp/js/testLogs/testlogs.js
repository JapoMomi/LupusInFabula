document.addEventListener('DOMContentLoaded', function (event) {
    loadLogs();
    loadStatics();
});


/**
 * Get logs.
 */
function loadLogs() {
    genericGETRequest(contextPath + "user/log_player/logs", getLogs)
}

function loadStatics() {
    genericGETRequest(contextPath + "user/log_player/statistic", getStatsRole)
    genericGETRequest(contextPath + "user/log_player/logs", getGeneralStats)
}

function getLogs(req) {
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status === HTTP_STATUS_OK) {

            var list = JSON.parse(req.responseText)["resource-list"]; //[JSON_resource_list]

            if (list == null)
                alert("User Not Authenticated");
            var table = document.getElementById("logs_table")

            //var tableBody = table.createTBody();
            var tbody = table.querySelector("tbody");

            var sizeid = 0;
            var sizerounds = 0;

            for (let i = 0; i < list.length; i++) {
                let log = list[i]['PlaysJoinGame'];
                var gameIdLength = log.game_id.toString().length;

                if (gameIdLength > sizeid) {
                    sizeid = gameIdLength;
                }

                var roundsLength = log.number_of_rounds.toString().length;
                if (roundsLength > sizerounds) {
                    sizerounds = roundsLength;
                }
            }

            for (let i = 0; i < list.length; i++) {

                let log = list[i]['PlaysJoinGame'];
                var row = tbody.insertRow();

                row.classList.add("item");

                var cell0 = row.insertCell(0);
                cell0.innerHTML = String(log.game_id).padStart(sizeid, '0');
                // cell0.innerHTML = Number(log.game_id);
                //cell0.classList.add("cell-with-zero");


                var cell1 = row.insertCell(1);
                cell1.innerHTML = log.start;

                var cell2 = row.insertCell(2);
                cell2.innerHTML = log.game_duration;

                var cell3 = row.insertCell(3);
                // cell3.innerHTML = Number(log.number_of_rounds);
                cell3.innerHTML = String(log.number_of_rounds).padStart(sizerounds, '0');
                //cell3.classList.add("cell-with-zero");

                var cell4 = row.insertCell(4);
                cell4.innerHTML = log.name;

                var cell5 = row.insertCell(5);
                if (log.has_won)
                    cell5.innerHTML = "Victory";
                else {
                    cell5.innerHTML = "Defeat";
                }

                var cell6 = row.insertCell(6);
                cell6.innerHTML = "Not working now";
            }
        }
    }
}


function getStatsRole(req) {
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status === HTTP_STATUS_OK) {
            var list = JSON.parse(req.responseText)["resource-list"];

            if (list == null)
                alert("User Not Authenticated");
            var table = document.getElementById("roles_table");
            var tbody = table.querySelector("tbody");

            var sizeCountName = 0;
            var sizeCountWins = 0;
            var size = 0;

            for (let i = 0; i < list.length; i++) {
                let stats = list[i]['StatsRole'];

                var countNameLength = stats.countName.toString().length;
                if (countNameLength > sizeCountName) {
                    sizeCountName = countNameLength;
                }

                var countWinsLength = stats.countWins.toString().length;
                if (countWinsLength > sizeCountWins) {
                    sizeCountWins = countWinsLength;
                }

                size +=  stats.countName;
            }

            size = (size == 0) ? 1 : size;

            const pairs = [];
            for (let i = 0; i < list.length; i++) {
                let stats = list[i]['StatsRole'];
                var row = tbody.insertRow();

                pairs.push([stats.name, stats.countName]);

                row.classList.add("item");

                var cell0 = row.insertCell(0);
                cell0.innerHTML = stats.name;

                var cell1 = row.insertCell(1);
                cell1.innerHTML = String(stats.countName).padStart(sizeCountName, '0');

                var cell2 = row.insertCell(2);
                cell2.innerHTML = String(stats.countWins).padStart(sizeCountWins, '0');

                var cell3 = row.insertCell(3);
                cell3.innerHTML = String(stats.countName - stats.countWins).padStart(sizeCountName);

                var cell4 = row.insertCell(4);
                cell4.innerHTML = (stats.countName/size).toFixed(3);

            }

            completePieChart(pairs);
        }
    }
}

function completePieChart(pairs){

    const ctx = document.getElementById('myChart');
    const names = pairs.map(pair => pair[0]);

    const rates = pairs.map(pair => pair[1]);


    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: names,
            datasets: [{
                label: 'Times of games played as',
                data: rates,
                borderWidth: 1
            }]
        },
        options: {
        }
    });
}

function getGeneralStats(req) {
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status === HTTP_STATUS_OK) {
            var table = document.getElementById("general_stats");

            var list = JSON.parse(req.responseText)["resource-list"];
            var totalPlayTime = "00:00:00";
            var totalGamesPlayed = list.length;
            var totalGamesWon = 0;
            for (let i = 0; i < list.length; i++) {
                let log = list[i]['PlaysJoinGame'];
                totalPlayTime = sumTime(totalPlayTime, log.game_duration);
                totalGamesWon += log.has_won ? 1 : 0;
            }

            let couple = [["Total time played", totalPlayTime], ["Games Played", totalGamesPlayed],
                ["Games Won", totalGamesWon], ["Games Lost", totalGamesPlayed-totalGamesWon],
                ["Ratio", (totalGamesWon/totalGamesPlayed).toFixed(3)]];
            for (i=0; i<couple.length; i++){
                var row = table.insertRow();

                var cell0 = row.insertCell(0);
                cell0.innerHTML = couple[i][0];
                if (couple[i][0] === "Ratio") {
                    cell0.innerHTML += '<div id="info_ratio" title="The percentage of games won over the total played">&#9432</div>';
                }

                var cell1 = row.insertCell(1);
                cell1.innerHTML = couple[i][1];
            }
        }
    }
}

function sumTime(time1, time2) {
    var secondTime1 = convertInSeconds(time1);
    var secondTime2 = convertInSeconds(time2);

    var sumSecond = secondTime1 + secondTime2;

    var hours = Math.floor(sumSecond / 3600);
    var minutes = Math.floor((sumSecond % 3600) / 60);
    var seconds = sumSecond % 60;

    var sizeHours = Math.max(hours, 99).toString().length; // Lunghezza massima delle ore: 99
    var sum = `${String(hours).padStart(sizeHours, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

    return sum;
}

function convertInSeconds(time) {
    var parts = time.split(":");
    var hours = parseInt(parts[0]);
    var minutes = parseInt(parts[1]);
    var seconds = parseInt(parts[2]);
    return hours * 3600 + minutes * 60 + seconds;
}
