//document.getElementById('search-button').addEventListener('click', function () {
//    var searchValue = document.getElementById('search-input').value.toLowerCase();
//    var table = document.getElementById('orders-table');
//    var rows = table.getElementsByTagName('tr');
//    for (var i = 1; i < rows.length; i++) {
//        var cells = rows[i].getElementsByTagName('td');
//        var match = false;
//        for (var j = 0; j < cells.length; j++) {
//            if (cells[j].innerText.toLowerCase().includes(searchValue)) {
//                match = true;
//                break;
//            }
//        }
//        rows[i].style.display = match ? '' : 'none';
//    }
//});
//
//document.getElementById('filter-button').addEventListener('click', function () {
//    var filterValue = document.getElementById('status-filter').value;
//    var table = document.getElementById('orders-table');
//    var rows = table.getElementsByTagName('tr');
//    for (var i = 1; i < rows.length; i++) {
//        var statusCell = rows[i].getElementsByTagName('td')[2]; // Cột trạng thái
//        rows[i].style.display = (statusCell && (statusCell.innerText === filterValue || filterValue === '')) ? '' : 'none';
//    }
//});
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('search-button').addEventListener('click', filterAndSearch);
    document.getElementById('filter-button').addEventListener('click', filterAndSearch);
});

function filterAndSearch() {
    var searchValue = document.getElementById('search-input').value.toLowerCase().trim();
    var filterValue = document.getElementById('status-filter').value.toLowerCase().trim();
    var table = document.getElementById('orders-table');
    var rows = table.getElementsByTagName('tr');

    for (var i = 1; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName('td');
        var statusCell = cells[2]; // Cột trạng thái
        var matchSearch = false;
        var matchFilter = false;
        
        // Kiểm tra điều kiện tìm kiếm
        for (var j = 0; j < cells.length; j++) {
            if (cells[j].innerText.toLowerCase().includes(searchValue)) {
                matchSearch = true;
                break;
            }
        }

        // Kiểm tra điều kiện lọc
        if (statusCell && (statusCell.innerText.toLowerCase().includes(filterValue) || filterValue === '')) {
            matchFilter = true;
        }

        // Hiển thị hoặc ẩn hàng dựa trên điều kiện tìm kiếm và lọc
        rows[i].style.display = (matchSearch && matchFilter) ? '' : 'none';
    }
}


