//    <![CDATA[
    

jQuery(document).ready(function($) {
    
    
    
    $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today newEvent',
                center: 'title',
                right: 'agendaDay,agendaWeek,listWeek'
            },
            defaultView: 'listWeek',
            minTime: '08:00:00',
            maxTime: '18:00:00',
            slotDuration: '00:15:00',
            slotLabelFormat: [
                    'dddd',
                    "HH:mm"
                ],
            hiddenDays: [0],
            eventOverlap: false,
            slotEventOverlap: false,
            handleWindowResize: true,   
            height: $(window).height() - 200, 
            slotLabelInterval: '01:00:00',
            slotLabelFormat: 'HH:mm',
            allDaySlot: false,
            eventLimit: true,
            events: events,
            customButtons: {
                    newEvent: {
                      text: 'Dodaj termin',
                      click: function() {
                        $('#mediumModal').modal('show');
                      }
                    }
                  },
    });
});
//    ]]>


//events = [];
//$.getJSON('/placowki/' + placowkaId + '/szczegoly/terminy', function(termin) {
//    events = termin;
//    console.log(events);
//});
//resources = [];
//$.getJSON('/placowki/' + placowkaId + '/szczegoly/gabinety', function(gabinet) {
//    resources = gabinet;
//});
//businessHours = [];
//$.getJSON('/placowki/' + placowkaId + '/szczegoly/otwarciePlacowek', function(godzinaOtwarcia) {
//    businessHours = godzinaOtwarcia;
//});
//
//$(document).ready($(function() {
//    var calendar = $('#calendar').fullCalendar({
//        //Podstawowe opcje kalendarza
//                defaultView: 'agendaDay',
//                contentHeight: 'auto',
//                allDaySlot: false,
//                aspectRatio: 1.8,
//                slotLabelFormat: [
//                    'dddd',
//                    "HH:mm"
//                ],
//                slotLabelInterval: 15,
//                editable: true,
//                selectable: true,
//                hiddenDays: [0],
//                eventOverlap: function(stillEvent, movingEvent) {
//                    return stillEvent.id !== movingEvent.constraint && stillEvent.id === movingEvent.id+'_block';
//                },
//                slotEventOverlap: false,
//                businessHours: businessHours,
//                header: {
//                    left: 'prev,next myCustomButton',
//                    center: 'title',
//                    right: 'agendaDay,timelineWeek'
//                },
//                events: events,
//                //Widowki
//                views: {
//                    agendaDay: {
//                        type: 'agenda',
//                        slotDuration: {
//                            minutes: 15
//                        },
//                        minTime: '06:00:00',
//                        maxTime: '21:00:00'
//                    },
//                    timelineWeek: {
//                        type: 'timeline',
//                        duration: {
//                            days: 7
//                        },
//                        slotDuration: {
//                            minutes: 15
//                        },
//                        minTime: '06:00:00',
//                        maxTime: '21:00:00',
//                        resourceAreaWidth: "10%"
//                    }
//                },
//                resourceLabelText: 'Gabinety',
//                resources: resources,
//                schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
//                
//                customButtons: {
//                    myCustomButton: {
//                      text: 'Dodaj termin',
//                      click: function() {
//                        $('#modal-default').modal('show');
//                      }
//                    }
//                  },
//
//                //Wy?????wietla opis terminu po najechaniu myszk?????
//                eventMouseover: function(calEvent, jsEvent) {
//                    myTerminHoverView(calEvent, jsEvent);
//                },
//                //Zjechanie myszk????? z eventu usuwa efekt wy?????wietlania opisu
//                eventMouseout: function(calEvent, jsEvent) {
//                    myTerminHoverViewDelete(calEvent, jsEvent);
//                },
//                //przenoszenie eventu
//                eventDrop: function(event) {
//                    myTerminTimelineEdit(event);
//                },
//                //Zmiana czasu trwania terminu
//                eventResize: function(event) {
//                    myTerminTimelineEdit(event);
//                },
//                //Usuwanie terminu
//                eventRender: function(event, element) {
//                    myTerminDelete(event, element);
//                },
//                //Edycja terminu
//                eventClick: function(event, element) {
//                    myTerminEdit(event, element);
//                }                   
//            });
//        }));
//        
//    //KONIEC KALENDARZA                           
//    //W?????asne funkcje wykorzystywane przez kalendarz
//             
//    //funckja odpowiada za wy?????wietlenie modal do edycji terminu
//    function myTerminEdit(event, element) {
//        
//      $('#terminEdit').modal('show');
//      $('#pracownikId').val(event.pracownikId).change();
//      $('#poradniaId').val(event.poradniaId).change();
//      $('#trybPracy').val(event.trybPracy).change();
//
//        
//        //Funkcja obs?????uguje przycisk w edycji terminu
//        $('#btnSaveEvent').unbind().click(function() {
//            var pracownik = $('#pracownikId').val();
//            var poradnia = $('#poradniaId').val();
//            var trybPracy = $('#trybPracy').val();
//            
//            $('#terminEdit').one('hidden.bs.modal', function (e) {
//               bootbox.confirm({
//                    message: "Czy chcesz edytowa????? termin",
//                    buttons: {
//                        confirm: {
//                            label: 'Tak',
//                            className: 'btn-success'
//                        },
//                        cancel: {
//                            label: 'Nie',
//                            className: 'btn-danger'
//                        }
//                    },
//                    callback: function(result) {
//                        if (result === true) {
//                            $.ajax({
//                                type: "POST",
//                                contentType: "application/x-www-form-urlencoded",
//                                url: '/placowki/' + placowkaId + '/szczegoly/terminyDaneUpdate',
//                                data: {
//                                    terminId: event.id,
//                                    pracownikId: pracownik,
//                                    poradniaId: poradnia,
//                                    trybPracy: trybPracy
//                                },
//                                success: function(data, xhr, textStatus) {
//                                    if(data === true) {
//                                        myNotifyTimeout("Termin zosta????? zmieniony", "success");
//                                    } else {
//                                        myNotifyTimeout("Edycja terminu zako?????czona niepowodzeniem", "error");
//                                    }
//                                },
//                                error: function(xhr, textStatus, error) {   
//                                      alert("Skontaktuj si????? z administratorem");
//                                }
//                              });
//                        } else {
//                            myNotifyTimeout("Anulowano edycj????? terminu", "warning");
//                    }
//                }
//            });
//        });
//            
//    });
//};
//
//    
//
//    //Funkcja odpowiada za usuwanie terminu z kalendarza
//    function myTerminDelete (event, element, view) {
//        element.find(".fc-content").prepend("<div style='position:absolute;top:-3px;right:-3px;'><button type='button' class='close' style='opacity:0.9;'><span style='color:white;font-size:22px;'>?????</span></button></div>");
//        element.find(".close").on('click', function() {
//            $('#calendar').fullCalendar('removeEvents',event._id);
//            bootbox.confirm({
//                message: "Czy chcesz usun?????????? termin",
//                buttons: {
//                    confirm: {
//                        label: 'Tak',
//                        className: 'btn-success'
//                    },
//                    cancel: {
//                        label: 'Nie',
//                        className: 'btn-danger'
//                    }
//                },
//                callback: function(result) {
//                    if (result === true) {
//                        $.ajax({
//                            type: "POST",
//                            contentType: "application/x-www-form-urlencoded",
//                            url: '/placowki/' + placowkaId + '/szczegoly/terminyDelete',
//                            data: {id: event.id},
//                            success: function(data, xhr, textStatus) {
//                                if(data === true) {
//                                    console.log(data);
//                            console.log(xhr);
//                            console.log(textStatus);
//                                    myNotifyTimeout("Termin zosta????? usuni?????ty", "success");
//                                } else {
//                                    myNotifyTimeout("Usuni?????cie terminu zako?????czone niepowodzeniem", "error");
//                                }
//                            },
//                            error: function(xhr, textStatus, error) {
//                                  alert("Skontaktuj si????? z administratorem");
//                            }
//                          });
//                    } else {
//                        myNotifyTimeout("Anulowano usuwanie terminu", "warning");
//                }
//            }
//        });
//    });
//}
//    
//    //funkcja odpowiada za edycj????? daty terminu
//    function myTerminTimelineEdit(event) {
//        bootbox.confirm({
//            message: "Czy chcesz zmieni????? dat????? terminu?",
//            buttons: {
//                confirm: {
//                    label: 'Tak',
//                    className: 'btn-success'
//                },
//                cancel: {
//                    label: 'Nie',
//                    className: 'btn-danger'
//                }
//            },
//            callback: function(result) {
//                if (result === true) {
//                    $.ajax({
//                        type: "POST",
//                        contentType: "application/x-www-form-urlencoded",
//                        url: '/placowki/' + placowkaId + '/szczegoly/terminyUpdate',
//                        data: {
//                            id: event.id,
//                            start: event.start.format('YYYY-MM-DDTHH:mm:ss'),
//                            end: event.end.format('YYYY-MM-DDTHH:mm:ss'),
//                            title: event.title,
//                            resourceId: event.resourceId
//                        },
//                        success: function(data, xhr, textStatus) {
//                            if(data === true) {
//                                myNotifyTimeout("Data zosta?????a zmieniona", "success");
//                            } else {
//                                myNotifyTimeout("Zmiana daty zako?????czona niepowodzeniem", "error");
//                            }
//                        },
//                        error: function(xhr, textStatus, error) {
//                              alert("Skontaktuj si????? z administratorem");
//                        }
//                      });
//                } else {
//                myNotifyTimeout("Anulowano zmian????? daty", "warning");
//            }
//        }
//    });
//};
//    
//    //Funkcja ob?????uguje najechanie myszk????? na termin i wy?????wietlenie opisu
//    function myTerminHoverView(calEvent, jsEvent) {
//        var tooltip = '<div class="direct-chat-text" style="position: absolute; z-index: 10001;">' + calEvent.title + '</div>';
//            var $tool = $(tooltip).appendTo('body');
//            $(this).mouseover(function(e) {
//                $(this).css('z-index', 10000);
//                $tool.fadeIn('500');
//                $tool.fadeTo('10', 1.9);
//            }).mousemove(function(e) {
//                $tool.css('top', e.pageY - 5);
//                $tool.css('left', e.pageX - 30);
//            });
//    };
//    
//    //Funkcja odpowiada za usuni?????cie opisu po zjechaniu myszk????? z terminu
//    function myTerminHoverViewDelete(calEvent, jsEvent) {
//        $(this).css('z-index', 8);
//        $('.direct-chat-text').remove();
//    };
//    
//    //funkcja wy?????wietla notyfikacje i od?????wie????a terminy
//    function myNotifyTimeout(comText, comType) {
//        setTimeout(function() {
//            $('.top-right').notify({
//                message: {
//                    text: comText
//                },
//                type: comType,
//                closable: false
//            }).show();
//        }, 600);
//        $('#calendar').fullCalendar('refetchEvents');
//    }
